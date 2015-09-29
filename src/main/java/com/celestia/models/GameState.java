
package com.celestia.models;

public class GameState {
    public final ICamera camera; 
    public final IGameWorld gameWorld;
    
    public GameState(ICamera camera, IGameWorld gameWorld) {
        this.camera = camera;
        this.gameWorld = gameWorld;
    }
}