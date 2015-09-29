
package com.celestia.interfaces;

import com.celestia.models.Camera;
import com.celestia.models.GameState;
import com.celestia.models.GameWorld;
import com.celestia.models.Triangle;

public interface IGameObjectFactory {
    public Triangle makeTriangle();
    public GameState initGameState();
    public GameWorld initGameWorld();
    public Camera initCamera();
}