
package com.celestia.models;

import java.util.List;

public class GLProgram {
    public final int programId;
    public final List<Integer> shaders;
    
    public GLProgram(int programId, List<Integer> shaders) {
        this.programId = programId;
        this.shaders = shaders;
    }
}
