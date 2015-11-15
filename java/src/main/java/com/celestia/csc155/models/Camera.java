
package com.celestia.csc155.models;
import graphicslib3D.*;
import com.jogamp.opengl.*;


public class Camera {
	private Vector3D position;
	
	private final Vector3D xAxis = new Vector3D(1, 0, 0);
	private final Vector3D yAxis = new Vector3D(0, 1, 0);
	private final Vector3D zAxis = new Vector3D(0, 0, 1);
	
	private Vector3D u = new Vector3D(0, 0, -1);
	private Vector3D v = new Vector3D(0, 1, 0);
	private Vector3D n = new Vector3D(-1, 0, 0);
    
    public Camera() {
        this.position = new Vector3D();
    }
    
    public Matrix3D getPosition() {
    	final Matrix3D matrix = new Matrix3D();
    	matrix.translate(
    		this.position.getX(),
    		this.position.getY(),
    		this.position.getZ()
    	);
    	
    	return matrix;
    }
    
    public Matrix3D getRotation() {
    	final Matrix3D rotation = new Matrix3D();
    	rotation.setRow(0, u);
    	rotation.setRow(1, v);
    	rotation.setRow(2, n);
    	return rotation;
    }
    
    
    
    public void translate(final double x, final double y, final double z) {
    	translate(new Vector3D(x, y, z));
    }
    
    
    public void translate(final Vector3D movement) {
    	final Vector3D delta_p = movement.mult(getRotation());
    	this.position = this.position.add(delta_p);
    }
    
    
    public void yaw(final double yaw) {
    	final Matrix3D rotation = new Matrix3D(yaw, this.v);
    	this.n = this.n.mult(rotation);
    	this.u = this.u.mult(rotation);
    }
    
    public void pitch(final double pitch) {
    	final Matrix3D rotation = new Matrix3D(pitch, this.u);
    	this.v = this.v.mult(rotation);
    	this.n = this.n.mult(rotation);
    }
    
    
    public void rotate(final double yaw, final double pitch, final double roll) {
    	this.yaw(yaw);
    	this.pitch(pitch);
    }
    
    
    
    /**
     * The view portion of the MVP Matrix!
     */
    public Matrix3D getViewTransform() {
    	final Matrix3D viewTransform = new Matrix3D();
    	viewTransform.concatenate(getRotation());
    	viewTransform.concatenate(getPosition());
    	return viewTransform;
    }
}






