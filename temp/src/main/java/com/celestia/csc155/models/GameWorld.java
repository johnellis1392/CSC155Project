
package com.celestia.csc155.models;

import java.util.List;

public class GameWorld {
    public final List<GameObject> gameObjects;
    
    public GameWorld(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
}
