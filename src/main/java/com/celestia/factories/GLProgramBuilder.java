
package com.celestia.factories; 

public class GLProgramBuilder implements IGLProgramBuilder {
    
    // public final int programId;
    public final List<Pair<String, int>> shaders;
    
    @Override 
    public IGLProgramBuilder addShader(String shaderPath, int shaderType) {
        
    }
    
    @Override 
    public GLProgram build() {
        return new GLProgram(programId, shaders);
    }
    
    public GLProgramBuilder() {
        // this.programId = -1;
        this.shaders = new List<Pair<String, int>>();
    }
    
    private GLProgramBuilder(List<Pair<String, int>> shaders) {
        this.shaders = shaders;
    }
}