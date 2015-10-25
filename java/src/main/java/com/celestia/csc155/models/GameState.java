
package com.celestia.csc155.models;
import java.util.List;
import com.celestia.csc155.models.*;
import com.celestia.csc155.interfaces.*;
import com.jogamp.opengl.*;

/**
 * Container class for game state
 * @author ellisj
 *
 */
public class GameState implements IGameState {
    private Camera camera;
    private List<IGameObject> gameWorld;
    
    public GameState(final Camera camera, final List<IGameObject> gameWorld) {
        this.camera = camera;
        this.gameWorld = gameWorld;
    }
    
    
    /**
     * Get the camera object
     */
    public final Camera getCamera() {
    	return this.camera;
    }
    
    
    /**
     * Get the current game world
     */
    public final List<IGameObject> getGameWorld() {
    	return this.gameWorld;
    }
    
    
	public void init(final GLAutoDrawable glAutoDrawable) {
		for(IGameObject gameObject : gameWorld) {
			gameObject.init(glAutoDrawable);
		}
	}
	
	public void update(final double time) {
		for(IGameObject gameObject : gameWorld) {
			gameObject.update(time);
		}
	}
	
	public void render(final GLAutoDrawable glAutoDrawable) {
		for(IGameObject gameObject : gameWorld) {
			gameObject.render(glAutoDrawable);
		}
	}
	
	public void dispose(final GLAutoDrawable glAutoDrawable) {
		for(IGameObject gameObject : gameWorld) {
			gameObject.dispose(glAutoDrawable);
		}
	}
}
