
package com.celestia.csc155.models;
import graphicslib3D.*;
import com.jogamp.opengl.*;


public class Camera {
	private double x;
	private double y;
	private double z;
	private double zoom;
	private Matrix3D rotation;
	private Matrix3D scale;
    
    public Camera() {
        this.rotation = new Matrix3D();
        this.scale = new Matrix3D();
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.zoom = 0.0;
    }
    
    public void translate(final double x, final double y, final double z) {
    	this.x += x;
    	this.y += y;
    	this.z += z;
    }
    
    public void rotate(final double roll, final double pitch, final double yaw) {
    	rotation.rotate(roll, pitch, yaw);
    }
    
    public void scale(final double x, final double y, final double z) {
    	scale.scale(x, y, z);
    }
    
    public final double getX() {
    	return this.x;
    }
    
    public final double getY() {
    	return this.y;
    }
    
    public final double getZ() {
    	return this.z;
    }

    public final double getZoom() {
    	return this.zoom;
    }
    
    public double setZoom(final double zoom) {
    	return this.zoom = zoom;
    }
    
    public Matrix3D getPosition() {
    	final Matrix3D position = new Matrix3D();
    	position.translate(x, y, z);
    	return position;
    }
}

