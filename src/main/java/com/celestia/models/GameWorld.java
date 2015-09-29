
package com.celestia.models;

public class GameWorld {
    public final List<IGameObject> gameObjects; 
    
    public GameWorld(List<IGameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
}
