
package com.celestia.csc155.models;

import com.celestia.csc155.interfaces.*;
import graphicslib3D.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import graphicslib3D.shape.*;
import com.celestia.csc155.interfaces.*;
import com.celestia.csc155.util.*;
import java.nio.*;
import java.util.*;

public class NTorus implements IGameObject {
	
	private Torus torus;
	private Material material;
	private final int[] buffer = new int[1];
	private final float[] vertices;
	private final int programId;
	
	public NTorus(final int programId) {
		this.programId = programId;
		this.torus = new Torus(0.5f, 0.2f, 100);
		this.material = Material.GOLD;
		final Vertex3D[] torusVertices = torus.getVertices();
		this.vertices = new float[torusVertices.length * 3];
		
		for(int i = 0; i < torusVertices.length; i++) {
			Vertex3D vertex = torusVertices[i];
			vertices[i * 3] = (float) vertex.getX();
			vertices[i * 3 + 1] = (float) vertex.getY();
			vertices[i * 3 + 2] = (float) vertex.getZ();
		}
	}
	
	
	public void update(final double time) {}
	public void render(final GLAutoDrawable glAutoDrawable) {
		final GL4 gl = (GL4) glAutoDrawable.getGL();
		
		final int material_ambient_location = gl.glGetUniformLocation(programId, R.uniforms.Material.ambient);
		final int material_diffuse_location = gl.glGetUniformLocation(programId, R.uniforms.Material.diffuse);
		final int material_specular_location = gl.glGetUniformLocation(programId, R.uniforms.Material.specular);
		final int material_shininess_location = gl.glGetUniformLocation(programId, R.uniforms.Material.shininess);
		
		gl.glProgramUniform4fv(programId, material_ambient_location, 1, material.getAmbient(), 0);
		gl.glProgramUniform4fv(programId, material_diffuse_location, 1, material.getDiffuse(), 0);
		gl.glProgramUniform4fv(programId, material_specular_location, 1, material.getSpecular(), 0);
		gl.glProgramUniform1f(programId, material_shininess_location, material.getShininess());
		
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, buffer[0]);
		gl.glVertexAttribPointer(0, 3, GL4.GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);
		
		gl.glDrawArrays(GL4.GL_TRIANGLES, 0, vertices.length);
	}
	
	
	public void init(final GLAutoDrawable glAutoDrawable) {
		final GL4 gl = (GL4) glAutoDrawable.getGL();
		
		gl.glGenVertexArrays(buffer.length, buffer, 0);
		gl.glBindVertexArray(buffer[0]);
		gl.glGenBuffers(buffer.length, buffer, 0);
		
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, buffer[0]);
		final FloatBuffer floatBuffer = FloatBuffer.wrap(vertices);
		gl.glBufferData(GL4.GL_ARRAY_BUFFER, floatBuffer.limit() * 4, floatBuffer, GL4.GL_STATIC_DRAW);
	}
	
	
	public void dispose(final GLAutoDrawable glAutoDrawable) {}
	
	public void translate(final double x, final double y, final double z) {}
	public void rotate(final double theta, final double phi, final double yaw) {}
	public void scale(final double x, final double y, final double z) {}
	
	public double getX() {
		return 0.0;
	}
	
	public double getY() {
		return 0.0;
	}
	
	public double getZ() {
		return 0.0;
	}
	
	public Point3D getPosition() {
		return new Point3D(0.0, 0.0, 0.0);
	}
	
	public void setMaterial(final Material material) {
		this.material = material;
	}
}

