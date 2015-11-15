
package com.celestia.csc155.util;

import java.util.*;

import com.celestia.csc155.factories.*;
import com.celestia.csc155.interfaces.*;
import com.celestia.csc155.models.*;
import com.celestia.csc155.providers.*;

import java.awt.*;
import java.awt.event.*;

import com.jogamp.opengl.*;
import graphicslib3D.*;
import graphicslib3D.light.*;
import graphicslib3D.shape.*;
import java.nio.FloatBuffer;

/**
 * Main class containing application logic
 * @author ellisj
 *
 */
public class GLEventHandler implements GLEventListener, MouseListener, KeyListener, MouseWheelListener, MouseMotionListener {

    private final GLProgramBuilder glProgramBuilder = new GLProgramBuilder();
    private IScriptProvider mScriptProvider = new ScriptProvider(); 
    private GLProgram glProgram;

    private Point mousePosition = new Point();
    private boolean hasFocus = false;
    private Robot robot;

    private PositionalLight positionalLight;
    private Material material;
    private IGameState mGameState;


    /**
     * Initialize GL Components
     */
    public void init(final GLAutoDrawable glAutoDrawable) {
        final GL4 gl = (GL4) glAutoDrawable.getGL();
        glProgram = glProgramBuilder
       		.addShader(R.shaders.vshader, IGLProgramBuilder.GL_VERTEX_SHADER)
            .addShader(R.shaders.fshader, IGLProgramBuilder.GL_FRAGMENT_SHADER)
            .addShader(R.shaders.gshader, IGLProgramBuilder.GL_GEOMETRY_SHADER)
            .build(glAutoDrawable);
        
        mScriptProvider = new ScriptProvider()
       		.addScript(R.ruby.main);
        
        final Camera camera = new Camera();
        final Triangle triangle = new Triangle(glProgram.programId);
        final Triangle triangle2 = new Triangle(glProgram.programId);
        final Cube cube = new Cube(glProgram.programId);
        
        cube.setMaterial(Material.BRONZE);
        triangle.setMaterial(Material.GOLD);
        triangle2.setMaterial(Material.SILVER);
        final NTorus torus = new NTorus(glProgram.programId);
//        torus.translate(0, 1.0, 0.0);
        
        camera.translate(0.0, 0.0, -10.0);
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
        gameWorld.add(torus);
        
        this.positionalLight = new PositionalLight();
        this.material = Material.GOLD;
        
        this.mGameState = new GameState(
        	camera,
        	gameWorld
        );
        
        mGameState.init(glAutoDrawable);
        
        try {
        	this.robot = new Robot();
        } catch(Exception e) {
        	e.printStackTrace();
        }
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
    	
    	drawAxes(glAutoDrawable);
    	positionalLight.setPosition(new Point3D(5.0f, 2.0f, 2.0f));
    	setupLights(glAutoDrawable);
    	
    	final int view_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.view_matrix);
    	final int projection_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.projection_matrix);
    	
    	final float aspect = (float) R.util.aspect;
    	final float fovy = 50.0f;
    	final Matrix3D projectionMatrix = perspective(fovy, aspect, 0.1f, 1000.0f);
    	final Matrix3D viewMatrix = mGameState.getCamera().getViewTransform();

    	
    	gl.glUniformMatrix4fv(view_location, 1, false, viewMatrix.getFloatValues(), 0);
    	gl.glUniformMatrix4fv(projection_location, 1, false, projectionMatrix.getFloatValues(), 0);
    	
    	runScripts(glAutoDrawable);
    	
    	mGameState.update(1.0);
    	mGameState.render(glAutoDrawable);
    }
    
    
    public void setupLights(final GLAutoDrawable glAutoDrawable) {
    	final GL4 gl = (GL4) glAutoDrawable.getGL();
		final Material currentMaterial = material;

		final Point3D lightP = positionalLight.getPosition();
		final Point3D lightPv = lightP.mult(mGameState.getCamera().getViewTransform());
		final float [] currLightPos = new float[] { (float) lightPv.getX(), (float) lightPv.getY(), (float) lightPv.getZ() };

		float [] globalAmbient = new float[] { 0.7f, 0.7f, 0.7f, 1.0f };
		
		// set the current globalAmbient settings
		final int global_ambient_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.global_ambient);
		gl.glProgramUniform4fv(glProgram.programId, global_ambient_location, 1, globalAmbient, 0);
	
		// get the locations of the light and material fields in the shader
		final int ambient_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.PositionalLight.ambient);
		final int diffuse_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.PositionalLight.diffuse);
		final int specular_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.PositionalLight.specular);
		final int position_location = gl.glGetUniformLocation(glProgram.programId, R.uniforms.PositionalLight.position);
	
		//  set the uniform light and material values in the shader
		gl.glProgramUniform4fv(glProgram.programId, ambient_location, 1, positionalLight.getAmbient(), 0);
		gl.glProgramUniform4fv(glProgram.programId, diffuse_location, 1, positionalLight.getDiffuse(), 0);
		gl.glProgramUniform4fv(glProgram.programId, specular_location, 1, positionalLight.getSpecular(), 0);
		gl.glProgramUniform3fv(glProgram.programId, position_location, 1, currLightPos, 0);
    }
    
    
    public void runScripts(final GLAutoDrawable glAutoDrawable) {
    	try {
    		final HashMap<String, Object> bindings = new HashMap<String, Object>();
    		bindings.put("glAutoDrawable", glAutoDrawable);
    		bindings.put("GameState", mGameState);
    		
    		mScriptProvider.eval(bindings);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    public void drawAxes(final GLAutoDrawable glAutoDrawable) {
    	final GL2 gl = (GL2) glAutoDrawable.getGL();
    	gl.glBegin(gl.GL_LINES);
    	
    	// Draw red x-axis
    	gl.glColor3d(1.0, 0.0, 0.0);
    	gl.glVertex3d(0.0, 0.0, 0.0);
    	gl.glVertex3d(10.0, 0.0, 0.0);
    	
    	// Draw green y-axis
    	gl.glColor3d(0.0, 1.0, 0.0);
    	gl.glVertex3d(0.0, 0.0, 0.0);
    	gl.glVertex3d(0.0, 10.0, 0.0);

    	// Draw blue z-axis
    	gl.glColor3d(0.0, 0.0, 1.0);
    	gl.glVertex3d(0.0, 0.0, 0.0);
    	gl.glVertex3d(0.0, 0.0, 10.0);
    	
    	gl.glEnd();
    	gl.glDrawArrays(GL4.GL_TRIANGLES, 0, 6);
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
    public void mousePressed(MouseEvent mouseEvent) {
    	this.hasFocus = true;
    }
    
    public void mouseReleased(MouseEvent mouseEvent) {}
    
    
    /**
     * Handle a key press
     */
    public void keyPressed(KeyEvent keyEvent) {
    	if(!this.hasFocus) return;
    	final int keyCode = keyEvent.getKeyCode();
    	final float speed = 0.1f;
    	final float rotationSpeed = -0.7f;
    	float dx = 0.0f;
    	float dy = 0.0f;
    	float dz = 0.0f;
    	float dYaw = 0.0f;
    	float dPitch = 0.0f;
    	
    	switch(keyCode) {
    	case KeyEvent.VK_W:
    		dz += speed;
    		break;
    	case KeyEvent.VK_S:
    		dz -= speed;
    		break;
    	case KeyEvent.VK_A:
    		dx += speed;
    		break;
    	case KeyEvent.VK_D:
    		dx -= speed;
    		break;
    	case KeyEvent.VK_E:
    		dy += speed;
    		break;
    	case KeyEvent.VK_Q:
    		dy -= speed;
    		break;
    	case KeyEvent.VK_ESCAPE:
    		this.hasFocus = false;
    		break;
    	case KeyEvent.VK_UP:
    		dPitch -= rotationSpeed;
    		break;
    	case KeyEvent.VK_DOWN:
    		dPitch += rotationSpeed;
    		break;
    	case KeyEvent.VK_LEFT:
    		dYaw -= rotationSpeed;
    		break;
    	case KeyEvent.VK_RIGHT:
    		dYaw += rotationSpeed;
    		break;
    	}
    	
    	this.mGameState.getCamera().translate(dx, dy, dz);
    	this.mGameState.getCamera().rotate(dYaw, dPitch, 0);
    }
    
    
    public void keyReleased(KeyEvent keyEvent) {}
    public void keyTyped(KeyEvent keyEvent) {}
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {}

    
    public void mouseMoved(final MouseEvent mouseEvent) {
//    	if(!this.hasFocus) return;
//    	final Point point = mouseEvent.getPoint();
//    	final double rotationSpeed = 0.11;
//    	
//    	final double deltaX = point.getX() - mousePosition.getX();
//    	final double deltaY = point.getY() - mousePosition.getY();
//    	
//    	mGameState.getCamera().rotate(
//    		-deltaY * rotationSpeed,
//    		-deltaX * rotationSpeed, 
//    		0
//    	);
//    	
//    	this.mousePosition = point;
//    	robot.mouseMove(700, 500);
    }
    
    
    
    public void mouseDragged(final MouseEvent mouseEvent) {}
}
























