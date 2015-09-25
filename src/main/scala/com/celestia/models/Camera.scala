
package com.celestia.models

import com.celestia.interfaces.ICamera
import graphicslib3D.Matrix3D

/**
 * Class for encapsulating the camera view
 * as a mutatable object
 * @param position
 * @param rotation
 * @param zoom
 */
case class Camera(
  override val position:Matrix3D,
  override val rotation:Matrix3D,
  override val zoom:Matrix3D 
) extends ICamera

