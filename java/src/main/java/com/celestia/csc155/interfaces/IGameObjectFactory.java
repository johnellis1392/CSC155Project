
package com.celestia.csc155.interfaces;

import com.celestia.csc155.models.Camera;
import com.celestia.csc155.models.GameState;
import com.celestia.csc155.models.GameWorld;
import com.celestia.csc155.models.Triangle;

public interface IGameObjectFactory {
    Triangle makeTriangle();
    GameState initGameState();
    GameWorld initGameWorld();
    Camera initCamera();
}