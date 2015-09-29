
package com.celestia.models;

public class GameState {
    public final Camera camera;
    public final GameWorld gameWorld;
    
    public GameState(Camera camera, GameWorld gameWorld) {
        this.camera = camera;
        this.gameWorld = gameWorld;
    }
}