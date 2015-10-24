
package com.celestia.services

import com.celestia.interfaces.{IGameWorld, IGameState, IUpdateService}
import com.celestia.models.{Camera, GameWorld, GameState}
import graphicslib3D.Matrix3D

/**
  * Service class for updating Game State
  */
class UpdateService extends IUpdateService {
  override def update(gameState:IGameState):IGameState={
    new GameState(
      update_camera(gameState.camera),
      gameState.gameWorld.map i => {
        case triangle:Triangle => {}
        case sphere:Sphere => {}
      }
    )
  }

  /*override def update(gameState:IGameState):IGameState={
    new GameState(
      new GameWorld(
        List()
      ),
      new Camera(
        gameState.camera.x,
        gameState.camera.y,
        gameState.camera.z
      )
    )
  }*/
}

