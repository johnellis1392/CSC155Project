
package com.celestia.strategies

import com.celestia.interfaces.{IGameObject, ICollisionStrategy}

class CollisionStrategy extends ICollisionStrategy {
  override def collide(g1: IGameObject, g2: IGameObject): IGameObject = {
    None
  }
}