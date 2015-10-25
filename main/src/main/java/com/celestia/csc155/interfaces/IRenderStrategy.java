
package com.celestia.csc155.interfaces;

import com.celestia.csc155.models.GameObject;
import com.jogamp.opengl.GLAutoDrawable;

public interface IRenderStrategy {
    public void render(GameObject gameObject, GLAutoDrawable glAutoDrawable);
}

