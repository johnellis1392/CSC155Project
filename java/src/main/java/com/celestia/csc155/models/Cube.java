
package com.celestia.csc155.models;

import com.celestia.csc155.interfaces.*;
import com.celestia.csc155.util.*;
import com.jogamp.opengl.*;
import graphicslib3D.*;
import java.nio.*;

public class Cube implements IGameObject {
	
	private Matrix3D position;
	private Matrix3D rotation;
	private Matrix3D scale;
	private int[] VAO = new int[1];
	private int[] VBO = new int[1];
	private static final int VERTEX_ATTRIB = 0;
	private int model_matrix_location;
	private final int programId;
	
	public Cube(final int programId) {
		this.position = new Matrix3D();
		this.rotation = new Matrix3D();
		this.scale = new Matrix3D();
		this.programId = programId;
	}
	
	public void update(final double time) {
		
	}
	
	
	public void init(final GLAutoDrawable glAutoDrawable) {
		final GL4 gl = (GL4) glAutoDrawable.getGL();
		model_matrix_location = gl.glGetUniformLocation(programId, R.uniforms.model_matrix);
        gl.glGenVertexArrays(VAO.length, VAO, 0);
        gl.glBindVertexArray(VAO[0]);
        gl.glGenBuffers(VBO.length, VBO, 0);

        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO[0]);
        FloatBuffer floatBuffer = FloatBuffer.wrap(R.vertices.cube);
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
    	
    	gl.glUniformMatrix4fv(model_matrix_location, 1, false, position.getFloatValues(), 0);
    	gl.glDrawArrays(GL4.GL_TRIANGLES, 0, R.vertices.cube.length);
	}
	
	

	
	public void dispose(final GLAutoDrawable glAutoDrawable) {
		
	}
	
	
	public void translate(final double x, final double y, final double z) {
		position.translate(x, y, z);
	}
	
	
	public void rotate(final double x, final double y, final double z) {
		rotation.rotate(x, y, z);
	}
	
	
	public void scale(final double x, final double y, final double z) {
		scale.scale(x, y, z);
	}
}

