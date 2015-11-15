
package com.celestia.csc155.interfaces;

import com.celestia.csc155.models.GLProgram;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;

public interface IGLProgramBuilder {

    int GL_VERTEX_SHADER = GL4.GL_VERTEX_SHADER;
    int GL_FRAGMENT_SHADER = GL4.GL_FRAGMENT_SHADER;
    int GL_GEOMETRY_SHADER = GL4.GL_GEOMETRY_SHADER;
    int GL_TESS_CONTROL_SHADER = GL4.GL_TESS_CONTROL_SHADER;
    int GL_TESS_EVALUATION_SHADER = GL4.GL_TESS_EVALUATION_SHADER;

    public IGLProgramBuilder addShader(String shaderPath, int shaderType);
    public GLProgram build(GLAutoDrawable glAutoDrawable);
}

