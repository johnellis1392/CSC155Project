
package com.celestia.csc155.models;

import graphicslib3D.*;
import com.jogamp.opengl.*;
import com.celestia.csc155.interfaces.*;
import com.celestia.csc155.util.*;
import java.nio.*;
import java.util.*;

/**
 * Created by celestia on 9/28/15.
 */
public class Triangle implements IGameObject, IOrbiter {

//	private Matrix3D position;
	private Point3D position;
	private Matrix3D rotation;
	private Matrix3D scale;
	private final int[] VAO = new int[1];
	private final int[] VBO = new int[1];
	private final int[] normal_buffer = new int[1];
	private static final int VERTEX_ATTRIB = 0;
	private static final int NORMAL_ATTRIB = 1;
	private final int programId;
	private double speed = 0.1;
	private Material material;
	private final float[] vertices = R.vertices.pyramid;
	private float[] normals;
	
	// Center for orbiting
	private IGameObject center = null;
	
	public Triangle(final int programId) {
		this.position = new Point3D();
		this.rotation = new Matrix3D();
		this.scale = new Matrix3D();
		this.programId = programId;
		this.material = Material.GOLD;
	}
	
	
	/**
	 * Spin the triangle about its axis
	 */
	public void update(final double time) {
		final Vector3D axis = new Vector3D(0, 1, 0);
		final double radius = position.distanceTo(center.getPosition());
		final Point3D pos = new Point3D(
			radius * Math.sin(speed * System.currentTimeMillis()) + center.getX(), 
			radius * Math.cos(speed * System.currentTimeMillis()) + center.getY(), 
			0.0
		);
		
		this.position = pos;
	}
	
	
	public void init(final GLAutoDrawable glAutoDrawable) {
		final GL4 gl = (GL4) glAutoDrawable.getGL();
		this.normals = getNormals(this.vertices);
		initNormalBuffer(normals, normal_buffer, glAutoDrawable);
		
        gl.glGenVertexArrays(VAO.length, VAO, 0);
        gl.glBindVertexArray(VAO[0]);
        gl.glGenBuffers(VBO.length, VBO, 0);

        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO[0]);
        final FloatBuffer floatBuffer = FloatBuffer.wrap(vertices);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, floatBuffer.limit() * 4, floatBuffer, GL4.GL_STATIC_DRAW);
	}
	
	
	private final void initNormalBuffer(final float[] normals, final int[] buffer, final GLAutoDrawable glAutoDrawable) {
		final GL4 gl = (GL4) glAutoDrawable.getGL();
		gl.glGenBuffers(buffer.length, buffer, 0);
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, buffer[0]);
		final FloatBuffer floatBuffer = FloatBuffer.wrap(normals);
		gl.glBufferData(GL.GL_ARRAY_BUFFER, floatBuffer.limit() * 4, floatBuffer, GL.GL_STATIC_DRAW);
	}
	
	
	private final float[] getNormals(final float[] vertices) {
		if(vertices.length % 3 != 0) return null;
		final ArrayList<Vector3D> normals = new ArrayList<Vector3D>();
		for(int i = 0; i < vertices.length / 3; i++) {
			Vertex3D vertex = new Vertex3D(new Point3D(
				vertices[i * 3],
				vertices[i * 3 + 1],
				vertices[i * 3 + 2]
			));
		}
		
		final float[] normal_array = new float[normals.size() * 3];
		for(int i = 0; i < normals.size(); i++) {
			final Vector3D vector = normals.get(i);
			normal_array[i * 3] = (float) vector.getX();
			normal_array[i * 3 + 1] = (float) vector.getY();
			normal_array[i * 3 + 2] = (float) vector.getZ();
		}
		
		return normal_array;
	}
	
	
	public void render(final GLAutoDrawable glAutoDrawable) {
		final GL4 gl = (GL4) glAutoDrawable.getGL();
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO[0]);
    	gl.glVertexAttribPointer(VERTEX_ATTRIB, 3, GL4.GL_FLOAT, false, 0, 0);
    	gl.glEnableVertexAttribArray(VERTEX_ATTRIB);
    	
//    	gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, normal_buffer[0]);
//    	gl.glVertexAttribPointer(NORMAL_ATTRIB, 3, GL4.GL_FLOAT, false, 0, 0);
//    	gl.glEnableVertexAttribArray(NORMAL_ATTRIB);
    	
    	gl.glEnable(GL4.GL_CULL_FACE);
    	gl.glFrontFace(GL4.GL_CW);
    	gl.glEnable(GL4.GL_DEPTH_TEST);
    	gl.glDepthFunc(GL4.GL_LEQUAL);

    	Matrix3D transform = new Matrix3D();
    	transform.concatenate(scale);
    	transform.concatenate(rotation);
    	transform.translate(getX(), getY(), getZ());
    	
    	final int material_ambient_location = gl.glGetUniformLocation(programId, R.uniforms.Material.ambient);
		final int material_diffuse_location = gl.glGetUniformLocation(programId, R.uniforms.Material.diffuse);
		final int material_specular_location = gl.glGetUniformLocation(programId, R.uniforms.Material.specular);
		final int material_shininess_location = gl.glGetUniformLocation(programId, R.uniforms.Material.shininess);
		
		gl.glProgramUniform4fv(programId, material_ambient_location, 1, material.getAmbient(), 0);
		gl.glProgramUniform4fv(programId, material_diffuse_location, 1, material.getDiffuse(), 0);
		gl.glProgramUniform4fv(programId, material_specular_location, 1, material.getSpecular(), 0);
		gl.glProgramUniform1f(programId, material_shininess_location, material.getShininess());
    	
    	final int model_matrix_location = gl.glGetUniformLocation(programId, R.uniforms.model_matrix);
    	gl.glUniformMatrix4fv(model_matrix_location, 1, false, transform.getFloatValues(), 0);
    	gl.glDrawArrays(GL4.GL_TRIANGLES, 0, R.vertices.pyramid.length);
	}

	
	
	public void dispose(final GLAutoDrawable glAutoDrawable) {
		
	}
	
	public void setMaterial(final Material material) {
		this.material = material;
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

