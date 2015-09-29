
package com.celestia.interfaces;

import com.celestia.models.GameObject;
import com.jogamp.opengl.GLAutoDrawable;

public interface IRenderStrategy {
    public void render(GameObject gameObject, GLAutoDrawable glAutoDrawable);
}

