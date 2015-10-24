
package com.celestia.csc155.factories;

import com.celestia.csc155.interfaces.IGLProgramBuilder;
import com.celestia.csc155.models.GLProgram;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;

import graphicslib3D.GLSLUtils;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GLProgramBuilder implements IGLProgramBuilder {

    // public final int programId;
    public final ArrayList<Pair<String, Integer>> shaders;

    private class Pair<A, B> {
        public final A a;
        public final B b;
        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }


    
    /**
     * Add a new shader to the current GLProgram collection
     */
    public IGLProgramBuilder addShader(String shaderPath, int shaderType) {
        ArrayList<Pair<String, Integer>> list = new ArrayList<Pair<String, Integer>>(shaders);
        list.add(new Pair<String, Integer>(shaderPath, shaderType));
        return new GLProgramBuilder(list);
    }
    
    
    /**
     * Create a new GLProgram object 
     */
    public GLProgram build(GLAutoDrawable glAutoDrawable) {
        GL4 gl = (GL4) glAutoDrawable.getGL();

        int[] compileStatus = new int[1];
        int[] linkStatus = new int[1];
        int programId = gl.glCreateProgram();
        ArrayList<Integer> shaderIds = new ArrayList<Integer>();
        
        
        // Iterate through shader collection and compile & attach shaders
        for(Pair<String, Integer> pair : shaders) {
            String[] shaderSource = GLSLUtils.readShaderSource(pair.a);
            int[] lineLengths = new int[shaderSource.length];
            for(int i=0; i < shaderSource.length; i++) {
                lineLengths[i] = shaderSource[i].length();
            }

            int shaderId = gl.glCreateShader(pair.b);
            gl.glShaderSource(shaderId, shaderSource.length, shaderSource, lineLengths, 0);
            gl.glCompileShader(shaderId);
            
            // Print GL Errors if there are any
            GLSLUtils.printOpenGLError(glAutoDrawable);
            gl.glGetShaderiv(shaderId, GL4.GL_COMPILE_STATUS, compileStatus, 0);
            if(compileStatus[0] == 1) {
            	System.out.println("Shader compiled successfully");
            } else {
            	System.out.println("Shader compilation failed");
            	GLSLUtils.printShaderInfoLog(glAutoDrawable, shaderId);
            }
            
            gl.glAttachShader(programId, shaderId);
            gl.glDeleteShader(shaderId);
            shaderIds.add(shaderId);
        }

        // Link program and Print GL Errors 
        gl.glLinkProgram(programId);
        
        GLSLUtils.printOpenGLError(glAutoDrawable);
        gl.glGetProgramiv(programId, GL4.GL_LINK_STATUS, linkStatus, 0);
        if(linkStatus[0] == 1) {
            System.out.println("Link success");
        } else {
        	System.out.println("Link failed");
        	GLSLUtils.printProgramInfoLog(glAutoDrawable, programId);
        }
        
        return new GLProgram(programId, shaderIds);
    }
    
    
    public GLProgramBuilder() {
        this.shaders = new ArrayList<Pair<String, Integer>>();
    }
    
    
    private GLProgramBuilder(ArrayList<Pair<String, Integer>> shaders) {
    	System.out.println(shaders.toString());
        this.shaders = shaders;
    }
}
