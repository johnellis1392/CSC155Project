
package com.celestia.csc155.models;

import com.celestia.csc155.interfaces.*;
import com.celestia.csc155.util.*;
import com.jogamp.opengl.*;
import graphicslib3D.*;
import java.nio.*;

public class Cube implements IGameObject {
	
	private Point3D position;
	private Matrix3D rotation;
	private Matrix3D scale;
	private int[] VAO = new int[1];
	private int[] VBO = new int[1];
	private static final int VERTEX_ATTRIB = 0;
	private Material material;
	private final int programId;
	private final float[] vertices = R.vertices.cube;
	
	public Cube(final int programId) {
		this.position = new Point3D();
		this.rotation = new Matrix3D();
		this.scale = new Matrix3D();
		this.programId = programId;
		this.material = Material.GOLD;
	}
	
	public void update(final double time) {
		
	}
	
	
	public void init(final GLAutoDrawable glAutoDrawable) {
		final GL4 gl = (GL4) glAutoDrawable.getGL();
		
        gl.glGenVertexArrays(VAO.length, VAO, 0);
        gl.glBindVertexArray(VAO[0]);
        gl.glGenBuffers(VBO.length, VBO, 0);

        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO[0]);
        FloatBuffer floatBuffer = FloatBuffer.wrap(vertices);
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
    	
    	final Matrix3D pos = new Matrix3D();
    	pos.concatenate(scale);
    	pos.concatenate(rotation);
    	pos.translate(getX(), getY(), getZ());
    	
    	final int material_ambient_location = gl.glGetUniformLocation(programId, R.uniforms.Material.ambient);
		final int material_diffuse_location = gl.glGetUniformLocation(programId, R.uniforms.Material.diffuse);
		final int material_specular_location = gl.glGetUniformLocation(programId, R.uniforms.Material.specular);
		final int material_shininess_location = gl.glGetUniformLocation(programId, R.uniforms.Material.shininess);
		
		gl.glProgramUniform4fv(programId, material_ambient_location, 1, material.getAmbient(), 0);
		gl.glProgramUniform4fv(programId, material_diffuse_location, 1, material.getDiffuse(), 0);
		gl.glProgramUniform4fv(programId, material_specular_location, 1, material.getSpecular(), 0);
		gl.glProgramUniform1f(programId, material_shininess_location, material.getShininess());
		
    	final int model_matrix_location = gl.glGetUniformLocation(programId, R.uniforms.model_matrix); 
    	gl.glUniformMatrix4fv(model_matrix_location, 1, false, pos.getFloatValues(), 0);
    	gl.glDrawArrays(GL4.GL_TRIANGLES, 0, R.vertices.cube.length);
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
}

