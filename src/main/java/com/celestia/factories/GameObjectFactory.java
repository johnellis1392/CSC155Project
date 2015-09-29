
package com.celestia.factories; 

public class GameObjectFactory implements IGameObjectFactory {
    
    @Override 
    public Triangle makeTriangle() {
        return new Triangle();
    }
    
    @Override 
    public IGameState initGameState() {
        return new GameState(
            initCamera(),
            initGameWorld()
        );
    }
    
    @Override 
    public IGameWorld initGameWorld() {
        return new GameWorld();
    }
    
    @Override 
    public ICamera initCamera() {
        return new Camera();
    }
}
