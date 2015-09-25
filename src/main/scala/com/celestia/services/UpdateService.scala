
package com.celestia.services

import com.celestia.interfaces.{IGameWorld, IGameState, IUpdateService}
import com.celestia.models.{Camera, GameWorld, GameState}
import graphicslib3D.Matrix3D

class UpdateService extends IUpdateService {
  override def update(gameState:IGameState):IGameState={
    new GameState(
      new GameWorld(
        List()
      ),
      new Camera(
        new Matrix3D(),
        new Matrix3D(),
        new Matrix3D()
      )
    )
  }
}
