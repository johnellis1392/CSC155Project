
package com.celestia.strategies

import com.celestia.factories.GameObjectFactory
import com.celestia.interfaces.{IGameObject, IUpdateStrategy}

class UpdateStrategy extends IUpdateStrategy {
  override def update(gameObject: IGameObject): IGameObject = {
    GameObjectFactory.makeTriangle
  }
}