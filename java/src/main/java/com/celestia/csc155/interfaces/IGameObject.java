package com.celestia.csc155.interfaces;
import com.jogamp.opengl.GLAutoDrawable;
import graphicslib3D.*;

/**
 * Interface defining base game object functionality 
 * @author ellisj
 *
 */
public interface IGameObject {
	void update(final double time);
	void render(final GLAutoDrawable glAutoDrawable);
	void init(final GLAutoDrawable glAutoDrawable);
	void dispose(final GLAutoDrawable glAutoDrawable);
	
	void translate(final double x, final double y, final double z);
	void rotate(final double theta, final double phi, final double yaw);
	void scale(final double x, final double y, final double z);
	
	double getX();
	double getY();
	double getZ();
	Point3D getPosition();
	void setMaterial(final Material material);
}
