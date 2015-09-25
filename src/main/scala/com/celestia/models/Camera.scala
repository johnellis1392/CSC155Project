
package com.celestia.models

import com.celestia.interfaces.ICamera

case class Camera(
  override val position:Matrix3D,
  override val rotation:Matrix3D,
  override val zoom:Matrix3D 
) extends ICamera

