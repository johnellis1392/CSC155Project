
package com.celestia.interfaces

import com.jogamp.opengl.{GLAutoDrawable, GL}
import graphicslib3D.Matrix3D

/**
  * Interface for defining a general game object. This is
  * the basemost definition of a game world object, defining 
  * how to move and manipulate an object. 
  */
trait IGameObject {
  // Case variables for game object state 
  def position:Matrix3D
  def rotation:Matrix3D
  def scale:Matrix3D
  def renderStrategy:IRenderStrategy
  def updateStrategy:IUpdateStrategy

  def update(elapsedTimeMS:Double):IGameObject
  def render(gl:GLAutoDrawable):Unit
}



