
package com.celestia.models

import com.celestia.interfaces.{IGameWorld, IGameObject}

case class GameWorld(
  override val gameObjects:List[IGameObject] 
) extends IGameWorld

