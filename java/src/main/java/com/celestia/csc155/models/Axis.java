
package com.celestia.csc155.models;

import graphicslib3D.*;
import com.jogamp.opengl.*;
import com.celestia.csc155.interfaces.*;
import com.celestia.csc155.util.*;
import java.nio.*;


public class Axis {//implements IGameObject {
	
	
	private static final float[] X_VERTS = new float[] {0.0f, 0.0f, 0.0f, 10.0f, 10.0f, 10.0f};
	private static final float[] Y_VERTS = new float[] {};
	private static final float[] Z_VERTS = new float[] {};
	
	private final int programId;
	
	public Axis(final int programId) {
		this.programId = programId;
	}
	
	public void update(final double time) {
		
	}
	
	public void render(final GLAutoDrawable glAutoDrawable) {
//		final GL4 gl = (GL4) glAutoDrawable.getGL();
//		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO[0]);
//    	gl.glVertexAttribPointer(VERTEX_ATTRIB, 3, GL4.GL_FLOAT, false, 0, 0);
//    	gl.glEnableVertexAttribArray(VERTEX_ATTRIB);
//    	
//    	gl.glEnable(GL4.GL_CULL_FACE);
//    	gl.glFrontFace(GL4.GL_CW);
//    	gl.glEnable(GL4.GL_DEPTH_TEST);
//    	gl.glDepthFunc(GL4.GL_LEQUAL);
//
//    	gl.glUniformMatrix4fv(model_matrix_location, 1, false, position.getFloatValues(), 0);
//    	gl.glDrawArrays(GL4.GL_LINES, 0, R.vertices.pyramid.length);
	}
	
	public void init(final GLAutoDrawable glAutoDrawable) {
		
	}
	
	public void dispose(final GLAutoDrawable glAutoDrawable) {
		
	}
	
	public void translate(final double x, final double y, final double z) {
		
	}
	
	public void rotate(final double theta, final double phi, final double yaw) {
		
	}
	
	public void scale(final double x, final double y, final double z) {
		
	}
}

