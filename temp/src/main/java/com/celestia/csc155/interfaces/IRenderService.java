
package com.celestia.csc155.interfaces;

import com.celestia.csc155.models.GameState;
import com.jogamp.opengl.GLAutoDrawable;

public interface IRenderService {
    public void render(GameState gameState, GLAutoDrawable glAutoDrawable);
}
