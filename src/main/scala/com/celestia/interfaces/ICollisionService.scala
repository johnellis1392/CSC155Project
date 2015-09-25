
package com.celestia.interfaces

trait ICollisionService {
  //def collide(g1:IGameObject, g2:IGameObject):Unit
  def detectCollisions(gameWorld:IGameWorld):IGameWorld  
}

