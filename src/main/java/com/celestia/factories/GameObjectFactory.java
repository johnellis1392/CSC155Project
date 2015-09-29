
package com.celestia.factories;

import com.celestia.interfaces.IGameObjectFactory;
import com.celestia.models.Camera;
import com.celestia.models.GameState;
import com.celestia.models.GameWorld;
import com.celestia.models.Triangle;

public class GameObjectFactory implements IGameObjectFactory {
    
    @Override 
    public Triangle makeTriangle() {
        return new Triangle();
    }
    
    @Override 
    public GameState initGameState() {
        return new GameState(
            initCamera(),
            initGameWorld()
        );
    }
    
    @Override 
    public GameWorld initGameWorld() {
        return new GameWorld();
    }
    
    @Override 
    public Camera initCamera() {
        return new Camera();
    }
}
