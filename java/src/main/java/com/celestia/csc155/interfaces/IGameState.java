
package com.celestia.csc155.interfaces;
import com.jogamp.opengl.*;
import java.util.List;
import com.celestia.csc155.models.*;

/**
 * Interface defining the game state 
 * @author ellisj
 *
 */
public interface IGameState {
	Camera getCamera();
	List<IGameObject> getGameWorld();
	void init(final GLAutoDrawable glAutoDrawable);
	void update(final double time);
	void render(final GLAutoDrawable glAutoDrawable);
	void dispose(final GLAutoDrawable glAutoDrawable);
}
