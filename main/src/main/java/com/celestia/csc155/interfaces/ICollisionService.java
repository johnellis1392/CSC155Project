
package com.celestia.csc155.interfaces;

import com.celestia.csc155.models.GameState;
import com.celestia.csc155.models.GameWorld;

public interface ICollisionService {
    GameState detectCollisions(GameState gameState);
}