
package com.celestia.factories

import com.celestia.interfaces.IGameObjectFactory
import com.celestia.models.Triangle
import com.celestia.strategies.{RenderStrategy, UpdateStrategy}
import graphicslib3D.Matrix3D

/**
 * Singleton Factory class for creating new Game
 * objects.
 */
object GameObjectFactory extends IGameObjectFactory {
  override def makeTriangle:Triangle = {
    new Triangle(
      new Matrix3D(),
      new Matrix3D(),
      new Matrix3D(),
      new UpdateStrategy(),
      new RenderStrategy()
    )
  }

}