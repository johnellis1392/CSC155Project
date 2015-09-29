
package com.celestia.factories;

import com.celestia.interfaces.IGLProgramBuilder;
import com.celestia.models.GLProgram;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import graphicslib3D.GLSLUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GLProgramBuilder implements IGLProgramBuilder {

    public static final int GL_VERTEX_SHADER = GL4.GL_VERTEX_SHADER;
    public static final int GL_FRAGMENT_SHADER = GL4.GL_FRAGMENT_SHADER;
    public static final int GL_GEOMETRY_SHDARE = GL4.GL_GEOMETRY_SHADER;
    public static final int GL_TESS_CONTROL_SHADER = GL4.GL_TESS_CONTROL_SHADER;
    public static final int GL_TESS_EVALUATION_SHADER = GL4.GL_TESS_EVALUATION_SHADER;

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


    @Override 
    public IGLProgramBuilder addShader(String shaderPath, int shaderType) {
        ArrayList<Pair<String, Integer>> list = new ArrayList<Pair<String, Integer>>(shaders);
        list.add(new Pair<String, Integer>(shaderPath, shaderType));
        return new GLProgramBuilder(shaders);
    }
    
    @Override 
    public GLProgram build(GLAutoDrawable glAutoDrawable) {
        GL4 gl = (GL4) glAutoDrawable.getGL();
        GLSLUtils glslUtils = new GLSLUtils();

        int programId = gl.glCreateProgram();
        ArrayList<Integer> shaderIds = new ArrayList<Integer>();

        for(Pair<String, Integer> pair : shaders) {
            String[] shaderSource = glslUtils.readShaderSource(pair.a);
            int[] lineLengths = new int[shaderSource.length];
            for(int i=0; i < shaderSource.length; i++) {
                lineLengths[i] = shaderSource[i].length();
            }

            int shaderId = gl.glCreateShader(pair.b);
            gl.glShaderSource(shaderId, shaderSource.length, shaderSource, lineLengths, 0);
            gl.glCompileShader(shaderId);
            gl.glAttachShader(programId, shaderId);
            gl.glDeleteShader(shaderId);
            shaderIds.add(shaderId);
        }

        return new GLProgram(programId, shaderIds);
    }
    
    public GLProgramBuilder() {
        this.shaders = new ArrayList<Pair<String, Integer>>();
    }
    
    private GLProgramBuilder(ArrayList<Pair<String, Integer>> shaders) {
        this.shaders = shaders;
    }
}