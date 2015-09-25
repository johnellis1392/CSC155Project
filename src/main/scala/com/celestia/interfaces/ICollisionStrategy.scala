
package com.celestia.interfaces

//import com.celestia.interfaces.IGameObject

trait ICollisionStrategy {
  def collide(g1:IGameObject, g2:IGameObject):IGameObject
}

