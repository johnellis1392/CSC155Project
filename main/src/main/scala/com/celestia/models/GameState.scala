
package com.celestia.models

import com.celestia.interfaces.{IGameState, ICamera, IGameWorld}

/**
 *
 * @param gameWorld
 * @param camera
 */
case class GameState(
  override val gameWorld:IGameWorld,
  override val camera:ICamera 
) extends IGameState

