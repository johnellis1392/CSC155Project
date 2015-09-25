package com.celestia.models

import com.celestia.factories.GameObjectFactory
import com.celestia.interfaces.{IGameObject, IRenderStrategy, IUpdateStrategy}
import com.celestia.models.GameObject
import com.jogamp.opengl.GLAutoDrawable

/**
 * Created by celestia on 9/24/15.
 */
class Triangle(
              position:Matrix3D,
              rotation:Matrix3D,
              scale:Matrix3D,
              updateStrategy:IUpdateStrategy,
              renderStrategy:IRenderStrategy
) extends GameObject(position, rotation, scale, updateStrategy, renderStrategy) {
  override def update(elapsedTimeMS:Double):IGameObject={
    new GameObjectFactory.makeTriangle()
  }

  override def render(gl:GLAutoDrawable):Unit={

  }
}
