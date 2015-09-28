
package com.celestia.models;

public class GLProgram {
    public final int programId;
    public final List<int> shaders; 
    
    public GLProgram(int programId, List<int> shaders) {
        this.programId = programId;
        this.shaders = shaders;
    }
}
