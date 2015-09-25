
package com.celestia.interfaces

import graphicslib3D.Matrix3D

trait ICamera {
  def position:Matrix3D
  def rotation:Matrix3D
  def zoom:Matrix3D 
}

