
package com.celestia.csc155.util;

import com.celestia.csc155.factories.GLProgramBuilder;
import com.celestia.csc155.factories.GameObjectFactory;
import com.celestia.csc155.interfaces.*;
import com.celestia.csc155.models.GLProgram;
import com.celestia.csc155.models.GameState;
import com.celestia.csc155.models.Camera;
import com.celestia.csc155.models.GameWorld;
import com.celestia.csc155.providers.ScriptProvider;
import com.celestia.csc155.providers.ServiceProvider;

import java.awt.event.*;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import graphicslib3D.Matrix3D;
import java.nio.FloatBuffer;

public class GLEventHandler implements GLEventListener, MouseListener, KeyListener {

    private final GLProgramBuilder glProgramBuilder = new GLProgramBuilder();
    private GLProgram glProgram;

    private final IGameObjectFactory mGameObjectFactory = new GameObjectFactory();
    private final IServiceProvider mServiceProvider = new ServiceProvider();
    private IScriptProvider mScriptProvider = new ScriptProvider();

    private final IUpdateService mUpdateService = mServiceProvider.getUpdateService();
    private final IRenderService mRenderService = mServiceProvider.getRenderService();
    private final ICollisionService mCollisionService = mServiceProvider.getCollisionService();

    private GameState mGameState = mGameObjectFactory.initGameState();

    private int[] VAO = new int[1];
    private int[] VBO = new int[1];

    private float x = 0.0f;
    private float y = -0.5f;
    private float z = 0.0f;


    /**
     * Initialize GL Components
     */
    public void init(final GLAutoDrawable glAutoDrawable) {
        final GL4 gl = (GL4) glAutoDrawable.getGL();
        glProgram = glProgramBuilder
        		.addShader(R.shaders.vshader, IGLProgramBuilder.GL_VERTEX_SHADER)
                .addShader(R.shaders.fshader, IGLProgramBuilder.GL_FRAGMENT_SHADER)
                .build(glAutoDrawable);
        
//        mScriptProvider = mScriptProvider.addScript(R.ruby.main);
//        try {
//            //mScriptProvider.call(R.ruby.callbacks.onInit, mGameState);
//            //mScriptProvider.eval(mGameState);
//            mScriptProvider.eval(glAutoDrawable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // Initialize Array buffers for Pyramid object 
        gl.glGenVertexArrays(VAO.length, VAO, 0);
        gl.glBindVertexArray(VAO[0]);
        gl.glGenBuffers(VBO.length, VBO, 0);

        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO[0]);
        FloatBuffer pyramidBuffer = FloatBuffer.wrap(R.vertices.cube);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, pyramidBuffer.limit() * 4, pyramidBuffer, GL4.GL_STATIC_DRAW);
    }

    
    
    /**
     * Update Game State
     * @param mGameState
     * @return
     */
    public GameState update(final GameState mGameState) {
    	final float r = 1.0f;
    	final double speed = 0.001f;
//    	final Camera camera = new Camera(
//    		(float) Math.sin(speed * System.currentTimeMillis()) * r,
//    		(float) Math.cos(speed * System.currentTimeMillis()) * r,
//    		1.0f
//    	);
    	
    	final Camera camera = new Camera(
    		mGameState.camera.x,
    		mGameState.camera.y,
    		mGameState.camera.z
    	);
    	

    	final GameWorld gameWorld = mGameState.gameWorld;
    	final GameState gameState = new GameState(
    		camera,
    		gameWorld
    	);
    	
    	return gameState;
    }
    
    

    /**
     * Render Game World
     */
    public void display(final GLAutoDrawable glAutoDrawable) {
    	final GL4 gl = (GL4) glAutoDrawable.getGL();
    	gl.glClear(GL4.GL_DEPTH_BUFFER_BIT);
    	gl.glUseProgram(glProgram.programId);
    	mGameState = update(mGameState); 
    	
//    	try {
//    		mScriptProvider.eval(glAutoDrawable);
//    	} catch(Exception e) {
//    		e.printStackTrace();
//    	}
    	
    	FloatBuffer background = FloatBuffer.allocate(4);
    	background.put(0, 0.0f);
    	background.put(1, 0.0f);
    	background.put(2, 0.0f);
    	background.put(3, 0.0f);
    	gl.glClearBufferfv(GL4.GL_COLOR, 0, background);
    	
    	final int model_view_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.model_view_matrix);
    	final int projection_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.projection_matrix);
    	
    	final float aspect = (float) R.util.aspect;
    	final Matrix3D projectionMatrix = perspective(50.0f, aspect, 0.1f, 1000.0f);
    	final Matrix3D viewMatrix = new Matrix3D();
    	
    	viewMatrix.translate(-mGameState.camera.x, -mGameState.camera.y, -mGameState.camera.z);
    	Matrix3D modelMatrix = new Matrix3D(); 
    	modelMatrix.translate(x, y, z);
    	
    	Matrix3D modelViewMatrix = new Matrix3D(); 
    	modelViewMatrix.concatenate(viewMatrix);
    	modelViewMatrix.concatenate(modelMatrix);
    	
    	gl.glUniformMatrix4fv(model_view_location, 1, false, modelViewMatrix.getFloatValues(), 0);
    	gl.glUniformMatrix4fv(projection_location, 1, false, projectionMatrix.getFloatValues(), 0);
    	
    	gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO[0]);
    	gl.glVertexAttribPointer(0, 3, GL4.GL_FLOAT, false, 0, 0);
    	gl.glEnableVertexAttribArray(0);
    	
    	gl.glEnable(GL4.GL_CULL_FACE);
    	gl.glFrontFace(GL4.GL_CW);
    	gl.glEnable(GL4.GL_DEPTH_TEST);
    	gl.glDepthFunc(GL4.GL_LEQUAL);
    	
    	gl.glDrawArrays(GL4.GL_TRIANGLES, 0, 36);
    }
    

    /**
     * Create a new Perspective Transform Matrix
     * @param fovy
     * @param aspect
     * @param n
     * @param f
     * @return
     */
    private Matrix3D perspective(float fovy, float aspect, float n, float f) {
        final float q = (float) (1.0f / Math.tan(Math.toRadians(0.5f * fovy)));
        final float A = q / aspect;
        final float B = (n + f) / (n - f);
        final float C = (2.0f * n * f) / (n - f);
        final Matrix3D r = new Matrix3D();

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
    }
    
    
    
    
    public void mouseExited(MouseEvent mouseEvent) {}
    public void mouseEntered(MouseEvent mouseEvent) {}
    public void mouseClicked(MouseEvent mouseEvent) {}
    public void mousePressed(MouseEvent mouseEvent) {}
    public void mouseReleased(MouseEvent mouseEvent) {}
    
    
    /**
     * Handle a key press
     */
    public void keyPressed(KeyEvent keyEvent) {
//    	System.out.println("Key Pressed: " + keyEvent);
    	
    	final int keyCode = keyEvent.getKeyCode();
    	final float speed = 0.01f;
    	float dx = mGameState.camera.x;
    	float dy = mGameState.camera.y;
    	float dz = mGameState.camera.z;
    	
    	switch(keyCode) {
    	case KeyEvent.VK_UP:
    		dy += speed;
    		break;
    	case KeyEvent.VK_DOWN:
    		dy -= speed;
    		break;
    	case KeyEvent.VK_LEFT:
    		dx -= speed;
    		break;
    	case KeyEvent.VK_RIGHT:
    		dx += speed;
    		break;
    	}
    	
    	
    	final Camera camera = new Camera(
    		dx,
    		dy,
    		dz
    	);
    	
    	final GameState gameState = new GameState(
    		camera,
    		mGameState.gameWorld
    	);
    	
    	// Reset Game State
    	this.mGameState = gameState;
    }
    
    public void keyReleased(KeyEvent keyEvent) {}
    public void keyTyped(KeyEvent keyEvent) {}
}
























