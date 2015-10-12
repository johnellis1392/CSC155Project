
package com.celestia.csc155.util;

import com.celestia.csc155.factories.GLProgramBuilder;
import com.celestia.csc155.factories.GameObjectFactory;
import com.celestia.csc155.interfaces.*;
import com.celestia.csc155.models.GLProgram;
import com.celestia.csc155.models.GameState;
import com.celestia.csc155.providers.ScriptProvider;
import com.celestia.csc155.providers.ServiceProvider;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import graphicslib3D.Matrix3D;
import java.nio.FloatBuffer;

public class GLEventHandler implements GLEventListener {

    private final GLProgramBuilder glProgramBuilder = new GLProgramBuilder();
    private GLProgram glProgram;

    private final IGameObjectFactory mGameObjectFactory = new GameObjectFactory();
    private final IServiceProvider mServiceProvider = new ServiceProvider();
    private IScriptProvider mScriptProvider = new ScriptProvider();

    private final IUpdateService mUpdateService = mServiceProvider.getUpdateService();
    private final IRenderService mRenderService = mServiceProvider.getRenderService();
    private final ICollisionService mCollisionService = mServiceProvider.getCollisionService();

    private GameState mGameState = mGameObjectFactory.initGameState();
//    private List<CompiledScript> compiledScripts;

    private int[] VAO = new int[1];
    private int[] VBO = new int[1];

    private float x = 0.0f;
    private float y = -0.5f;
    private float z = 0.0f;


    /**
     * Initialize GL Components
     */
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = (GL2) glAutoDrawable.getGL();
        //glProgram = glProgramBuilder
        //		.addShader(R.shaders.vshader, IGLProgramBuilder.GL_VERTEX_SHADER)
        //        .addShader(R.shaders.fshader, IGLProgramBuilder.GL_FRAGMENT_SHADER)
        //        .build(glAutoDrawable);
        
        mScriptProvider = mScriptProvider.addScript(R.ruby.main);
        try {
            //mScriptProvider.call(R.ruby.callbacks.onInit, mGameState);
            //mScriptProvider.eval(mGameState);
            mScriptProvider.eval(glAutoDrawable);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize Array buffers for Pyramid object 
        gl.glGenVertexArrays(VAO.length, VAO, 0);
        gl.glBindVertexArray(VAO[0]);
        gl.glGenBuffers(VBO.length, VBO, 0);

        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO[0]);
        FloatBuffer pyramidBuffer = FloatBuffer.wrap(R.vertices.pyramid);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, pyramidBuffer.limit() * 4, pyramidBuffer, GL4.GL_STATIC_DRAW);
    }

    
    /**
     * Update Game State
     * @param mGameState
     * @return
     */
    public GameState update(final GameState mGameState) {
    	GameState gameState = null; 
    	gameState = mUpdateService.update(mGameState);
    	gameState = mCollisionService.detectCollisions(gameState);
    	
    	try {
            mScriptProvider.call(R.ruby.callbacks.onUpdate, gameState);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    	
    	return gameState;
    }
    

    /**
     * Render Game World
     */
    public void display(final GLAutoDrawable glAutoDrawable) {
//    	mGameState = update(mGameState);
    	GL2 gl = (GL2) glAutoDrawable.getGL();
    	gl.glClear(GL4.GL_DEPTH_BUFFER_BIT);
        
        try {
            mScriptProvider.eval(glAutoDrawable);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    	//gl.glUseProgram(glProgram.programId);
    	
    	//mRenderService.render(mGameState, glAutoDrawable);
    	
//    	try {
//			mScriptProvider.call(R.ruby.callbacks.onDisplay, glAutoDrawable);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 

//    	gl.glPointSize(40.0f);
    	gl.glDrawArrays(GL4.GL_TRIANGLES, 0, 1);
    }
    
    
//    public void display(GLAutoDrawable glAutoDrawable) {
//    	GL2 gl = (GL2) glAutoDrawable.getGL();
//    	gl.glClear(GL4.GL_DEPTH_BUFFER_BIT);
//    	gl.glUseProgram(glProgram.programId);
//    	
//    	int model_view_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.model_view_matrix);
//    	int projection_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.projection_matrix);
//    	
//    	float aspect = (float) R.util.aspect;
//    	Matrix3D projectionMatrix = perspective(50.0f, aspect, 0.1f, 1000.0f);
//    	Matrix3D viewMatrix = new Matrix3D();
//    	viewMatrix.translate(-mGameState.camera.x, -mGameState.camera.y, -mGameState.camera.z);
//    	Matrix3D modelMatrix = new Matrix3D(); 
//    	modelMatrix.translate(x, y, z);
//    	
//    	Matrix3D modelViewMatrix = new Matrix3D(); 
//    	modelViewMatrix.concatenate(viewMatrix);
//    	modelViewMatrix.concatenate(modelMatrix);
//    	
//    	gl.glUniformMatrix4fv(model_view_location, 1, false, modelViewMatrix.getFloatValues(), 0);
//    	gl.glUniformMatrix4fv(projection_location, 1, false, projectionMatrix.getFloatValues(), 0);
//    	
//    	gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO[0]);
//    	gl.glVertexAttribPointer(0, 3, GL4.GL_FLOAT, false, 0, 0);
//    	gl.glEnableVertexAttribArray(0);
//    	
//    	gl.glEnable(GL4.GL_CULL_FACE);
//    	gl.glFrontFace(GL4.GL_CW);
//    	gl.glEnable(GL4.GL_DEPTH_TEST);
//    	gl.glDepthFunc(GL4.GL_LEQUAL);
//    	
//    	gl.glDrawArrays(GL4.GL_TRIANGLES, 0, 36);
////    	gl.glDrawArrays(GL4.GL_POINTS, 0, 1);
//    }
    
    
//    public void display(GLAutoDrawable glAutoDrawable) {
//        GL2 gl = (GL2) glAutoDrawable.getGL();
//        //mGameState = mUpdateService.update(mGameState);
//        //mCollisionService.detectCollisions(mGameState.gameWorld);
//
//        gl.glClear(GL4.GL_DEPTH_BUFFER_BIT);
//        FloatBuffer background = FloatBuffer.allocate(4);
////        background.put(0, (float) Math.sin(System.currentTimeMillis()) * 0.5f + 0.5f);
////        background.put(1, (float) Math.cos(System.currentTimeMillis()) * 0.5f + 0.5f);
//        background.put(0, 1.0f);
//        background.put(1, 1.0f);
//        background.put(2, 1.0f);
//        background.put(3, 1.0f);
//        gl.glClearBufferfv(GL4.GL_COLOR, 0, background);
//        gl.glUseProgram(glProgram.programId);
//        
//        try {
//			mScriptProvider.eval(mGameState);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (ScriptException e) {
//			e.printStackTrace();
//		}
//
//    //    int mvLocation = gl.glGetUniformLocation(glProgram.programId, R.gl.uniforms.model_view_matrix)
//    //    int projectionLocation = gl.glGetUniformLocation(glProgram.programId, R.gl.uniforms.projection_matrix)
//
//        Matrix3D viewMatrix = new Matrix3D();
//        viewMatrix.translate(
//          -mGameState.camera.x,
//          -mGameState.camera.y,
//          -mGameState.camera.z
//        );
//
//        Matrix3D modelMatrix = new Matrix3D();
//    //    double amount = System.currentTimeMillis() % 3600 / 10.0
//
//    //    modelMatrix.rotate(amount, amount, amount);
//        modelMatrix.translate(x, y, z);
//        modelMatrix.rotateX(-20.0f);
//        modelMatrix.rotateY(35.0f);
//
//        Matrix3D modelViewMatrix = new Matrix3D();
//        modelViewMatrix.concatenate(viewMatrix);
//        modelViewMatrix.concatenate(modelMatrix);
//
//    //    val aspect:Float = R.util.aspect.toFloat
//    //    val projectionMatrix:Matrix3D = perspective(50.0f, aspect, 0.1f, 1000.0f)
//
//    //    gl.glUniformMatrix4fv(mvLocation, 1, false, modelViewMatrix.getFloatValues, 0)
//    //    gl.glUniformMatrix4fv(projectionLocation, 1, false, projectionMatrix.getFloatValues, 0)
//
//        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO[0]);
//        gl.glVertexAttribPointer(0, 3, GL4.GL_FLOAT, false, 0, 0);
//        gl.glEnableVertexAttribArray(1);
//
//        gl.glEnable(GL4.GL_CULL_FACE);
//        gl.glFrontFace(GL4.GL_CCW);
//        gl.glEnable(GL4.GL_DEPTH_TEST);
//        gl.glDepthFunc(GL4.GL_LEQUAL);
//
//        gl.glDrawArrays(GL4.GL_TRIANGLES, 0, 18);
//        mRenderService.render(mGameState, glAutoDrawable);
//    }
    
    

    /**
     * Create a new Perspective Transform Matrix
     * @param fovy
     * @param aspect
     * @param n
     * @param f
     * @return
     */
    private Matrix3D perspective(float fovy, float aspect, float n, float f) {
        float q = (float) (1.0f / Math.tan(Math.toRadians(0.5f * fovy)));
        float A = q / aspect;
        float B = (n + f) / (n - f);
        float C = (2.0f * n * f) / (n - f);
        Matrix3D r= new Matrix3D();

        r.setElementAt(0,0,A);
        r.setElementAt(1,1,q);
        r.setElementAt(2,2,B);
        r.setElementAt(2,3,-1.0f);
        r.setElementAt(3,2,C);
        return r.transpose();
    }

    
    
    /**
     * Reshape the screen
     */
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int w, int h) {
        
    }


    
    /**
     * Dispose of graphics objects
     */
    public void dispose(GLAutoDrawable glAutoDrawable) {
        GL4 gl = (GL4) glAutoDrawable.getGL();
        gl.glDeleteProgram(glProgram.programId);
        
        try {
			mScriptProvider.call(R.ruby.callbacks.onDispose, glAutoDrawable);
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
}

