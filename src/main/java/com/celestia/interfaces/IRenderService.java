
package com.celestia.interfaces;

import com.jogamp.opengl.GLAutoDrawable;

public interface IRenderService {
    public void render(IGameState gameState, GLAutoDrawable glAutoDrawable);
}
