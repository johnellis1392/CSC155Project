
package com.celestia.csc155.factories;

import com.celestia.csc155.interfaces.IGameObjectFactory;
import com.celestia.csc155.models.*;

import java.util.ArrayList;
import java.util.List;

public class GameObjectFactory implements IGameObjectFactory {
    
    public Triangle makeTriangle() {
        return new Triangle();
    }
    
    
    public GameState initGameState() {
        return new GameState(
            initCamera(),
            initGameWorld()
        );
    }
    
    
    public GameWorld initGameWorld() {
        return new GameWorld(new ArrayList<GameObject>());
    }
    
    
    public Camera initCamera() {
        return new Camera(0.0f, 0.0f, 1.0f);
    }
}
