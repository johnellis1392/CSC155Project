
package com.celestia.csc155.util;

import java.util.List;
import java.util.ArrayList;

import com.celestia.csc155.factories.*;
import com.celestia.csc155.interfaces.*;
import com.celestia.csc155.models.*;
import com.celestia.csc155.providers.*;

import java.awt.event.*;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import graphicslib3D.Matrix3D;
import java.nio.FloatBuffer;

/**
 * Main class containing application logic
 * @author ellisj
 *
 */
public class GLEventHandler implements GLEventListener, MouseListener, KeyListener, MouseWheelListener {

    private final GLProgramBuilder glProgramBuilder = new GLProgramBuilder();
    private GLProgram glProgram;

//    private final IServiceProvider mServiceProvider = new ServiceProvider();
//    private IScriptProvider mScriptProvider = new ScriptProvider();

//    private final IUpdateService mUpdateService = mServiceProvider.getUpdateService();
//    private final IRenderService mRenderService = mServiceProvider.getRenderService();
//    private final ICollisionService mCollisionService = mServiceProvider.getCollisionService();

    private IGameState mGameState;


    /**
     * Initialize GL Components
     */
    public void init(final GLAutoDrawable glAutoDrawable) {
        final GL4 gl = (GL4) glAutoDrawable.getGL();
        glProgram = glProgramBuilder
        		.addShader(R.shaders.vshader, IGLProgramBuilder.GL_VERTEX_SHADER)
                .addShader(R.shaders.fshader, IGLProgramBuilder.GL_FRAGMENT_SHADER)
                .build(glAutoDrawable);
        
        final Camera camera = new Camera();
        final Triangle triangle = new Triangle(glProgram.programId);
        final Triangle triangle2 = new Triangle(glProgram.programId);
        final Cube cube = new Cube(glProgram.programId);
//        final IGameObject axis = new Axis(glProgram.programId);
        
        camera.translate(0.0, 0.0, 0.0);
        triangle.translate(1, 0, 0);
        cube.translate(-1, 0, 0);
        ((IOrbiter) triangle).setCenter(cube);
        ((IOrbiter) triangle2).setCenter(triangle);
        triangle.setSpeed(0.001);
        triangle2.setSpeed(0.002);
        
        final ArrayList<IGameObject> gameWorld = new ArrayList<IGameObject>();
        gameWorld.add(triangle);
        gameWorld.add(triangle2);
        gameWorld.add(cube);
//        gameWorld.add(axis);
        
        this.mGameState = new GameState(
        	camera,
        	gameWorld
        );
        
        mGameState.init(glAutoDrawable);
    }

    
    
    /**
     * Update Game State
     * @param mGameState
     * @return
     */
    public void update(GameState mGameState) {
    	final float r = 1.0f;
    	final double speed = 0.001f;
    }
    
    

    /**
     * Render Game World
     */
    public void display(final GLAutoDrawable glAutoDrawable) {
    	final GL4 gl = (GL4) glAutoDrawable.getGL();
    	gl.glClear(GL4.GL_DEPTH_BUFFER_BIT);
    	gl.glUseProgram(glProgram.programId);
    	mGameState.update(1.0);
    	
    	FloatBuffer background = FloatBuffer.allocate(4);
    	background.put(0, 0.0f);
    	background.put(1, 0.0f);
    	background.put(2, 0.0f);
    	background.put(3, 0.0f);
    	gl.glClearBufferfv(GL4.GL_COLOR, 0, background);
    	
    	final int view_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.view_matrix);
    	final int projection_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.projection_matrix);
    	
    	final float aspect = (float) R.util.aspect;
    	final float fovy = 50.0f;
    	final Matrix3D projectionMatrix = perspective(fovy, aspect, 0.1f, 1000.0f);
    	final Matrix3D viewMatrix = new Matrix3D();
    	
    	viewMatrix.translate(
    		-mGameState.getCamera().getX(), 
    		-mGameState.getCamera().getY(), 
    		-mGameState.getCamera().getZ()
    	);
    	
    	gl.glUniformMatrix4fv(view_location, 1, false, viewMatrix.getFloatValues(), 0);
    	gl.glUniformMatrix4fv(projection_location, 1, false, projectionMatrix.getFloatValues(), 0);
    	
    	mGameState.update(1.0);
    	mGameState.render(glAutoDrawable);
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
        mGameState.dispose(glAutoDrawable);
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
    	final int keyCode = keyEvent.getKeyCode();
    	final float speed = 0.02f;
    	float dx = 0.0f;
    	float dy = 0.0f;
    	float dz = 0.0f;
    	
    	switch(keyCode) {
    	case KeyEvent.VK_W:
    	case KeyEvent.VK_UP:
    		dy += speed;
    		break;
    	case KeyEvent.VK_S:
    	case KeyEvent.VK_DOWN:
    		dy -= speed;
    		break;
    	case KeyEvent.VK_A:
    	case KeyEvent.VK_LEFT:
    		dx -= speed;
    		break;
    	case KeyEvent.VK_D:
    	case KeyEvent.VK_RIGHT:
    		dx += speed;
    		break;
    	}
    	
    	this.mGameState.getCamera().translate(dx, dy, dz);
    }
    
    public void keyReleased(KeyEvent keyEvent) {}
    public void keyTyped(KeyEvent keyEvent) {}
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
    	final float speed = 1.0f;
    	final float mouseWheelMovement = (float) mouseWheelEvent.getWheelRotation();
    	final float zoom = speed * mouseWheelMovement;
    	mGameState.getCamera().translate(0, 0, zoom);
    }
}
























