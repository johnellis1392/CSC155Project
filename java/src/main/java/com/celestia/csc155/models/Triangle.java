
package com.celestia.csc155.models;

import graphicslib3D.*;
import com.jogamp.opengl.*;
import com.celestia.csc155.interfaces.*;
import com.celestia.csc155.util.*;
import java.nio.*;
import java.util.Random;

/**
 * Created by celestia on 9/28/15.
 */
public class Triangle implements IGameObject, IOrbiter {

//	private Matrix3D position;
	private Point3D position;
	private Matrix3D rotation;
	private Matrix3D scale;
	private int[] VAO = new int[1];
	private int[] VBO = new int[1];
	private static final int VERTEX_ATTRIB = 0;
	private int model_matrix_location;
	private final int programId;
	private double speed = 0.1;
	
	// Center for orbiting
	private IGameObject center = null;
	
	public Triangle(final int programId) {
//		this.position = new Matrix3D();
		this.position = new Point3D();
		this.rotation = new Matrix3D();
		this.scale = new Matrix3D();
		this.programId = programId;
	}
	
	
	/**
	 * Spin the triangle about its axis
	 */
	public void update(final double time) {
		final Vector3D axis = new Vector3D(0, 1, 0);
		final double radius = position.distanceTo(center.getPosition());
//		final double speed = 0.002;
//		final Matrix3D position = new Matrix3D();
		final Point3D pos = new Point3D(
			radius * Math.sin(speed * System.currentTimeMillis()) + center.getX(), 
			radius * Math.cos(speed * System.currentTimeMillis()) + center.getY(), 
			0.0
		);
		
		this.position = pos;
//		rotation.rotate(1.0, new Vector3D(0, 1, 0));
//		scale.scale(1.0, 1.0, 1.0);
	}
	
	
	public void init(final GLAutoDrawable glAutoDrawable) {
		final GL4 gl = (GL4) glAutoDrawable.getGL();
		model_matrix_location = gl.glGetUniformLocation(programId, R.uniforms.model_matrix);
        gl.glGenVertexArrays(VAO.length, VAO, 0);
        gl.glBindVertexArray(VAO[0]);
        gl.glGenBuffers(VBO.length, VBO, 0);

        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO[0]);
        FloatBuffer floatBuffer = FloatBuffer.wrap(R.vertices.pyramid);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, floatBuffer.limit() * 4, floatBuffer, GL4.GL_STATIC_DRAW);
	}
	
	
	public void render(final GLAutoDrawable glAutoDrawable) {
		final GL4 gl = (GL4) glAutoDrawable.getGL();
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO[0]);
    	gl.glVertexAttribPointer(VERTEX_ATTRIB, 3, GL4.GL_FLOAT, false, 0, 0);
    	gl.glEnableVertexAttribArray(VERTEX_ATTRIB);
    	
    	gl.glEnable(GL4.GL_CULL_FACE);
    	gl.glFrontFace(GL4.GL_CW);
    	gl.glEnable(GL4.GL_DEPTH_TEST);
    	gl.glDepthFunc(GL4.GL_LEQUAL);

    	Matrix3D transform = new Matrix3D();
    	transform.concatenate(scale);
    	transform.concatenate(rotation);
    	transform.translate(getX(), getY(), getZ());
    	
    	gl.glUniformMatrix4fv(model_matrix_location, 1, false, transform.getFloatValues(), 0);
    	gl.glDrawArrays(GL4.GL_TRIANGLES, 0, R.vertices.pyramid.length);
	}

	
	
	public void dispose(final GLAutoDrawable glAutoDrawable) {
		
	}
	
	
	public void translate(final double x, final double y, final double z) {
		position = position.add(new Point3D(x, y, z));
	}
	
	
	public void rotate(final double x, final double y, final double z) {
		rotation.rotate(x, y, z);
	}
	
	
	public void scale(final double x, final double y, final double z) {
		scale.scale(x, y, z);
	}
	
	public void setCenter(final IGameObject gameObject) {
		this.center = gameObject;
	}
	
	public double getX() {
		return this.position.getX();
	}
	
	public double getY() {
		return this.position.getY();
	}
	
	public double getZ() {
		return this.position.getZ();
	}
	
	public Point3D getPosition() {
		return this.position;
	}
	
	public void setSpeed(final double speed) {
		this.speed = speed;
	}
}

