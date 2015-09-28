
package com.celestia.interfaces; 

public interface IGLProgramBuilder {
    public IGLProgramBuilder addShader(String shaderPath, int shaderType);
    public GLProgram build();
}

