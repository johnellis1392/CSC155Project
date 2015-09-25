
package com.celestia.models

import com.celestia.interfaces.{IRenderStrategy, IUpdateStrategy, IGameObject}

/**
  * Game Object class for holding state of an
  * individual game object. 
  */
abstract case class GameObject(
  override val position:Matrix3D,
  override val rotation:Matrix3D,
  override val scale:Matrix3D,

  override val updateStrategy:IUpdateStrategy,
  override val renderStrategy:IRenderStrategy 
) extends IGameObject

