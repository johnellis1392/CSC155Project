
package com.celestia.interfaces;

import com.celestia.models.GLProgram;
import com.jogamp.opengl.GLAutoDrawable;

public interface IGLProgramBuilder {
    public IGLProgramBuilder addShader(String shaderPath, int shaderType);
    public GLProgram build(GLAutoDrawable glAutoDrawable);
}

