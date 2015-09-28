
package com.celestia.models;

public class Camera implements ICamera {
    public final float x; 
    public final float y; 
    public final float z; 
    
    public Camera(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

