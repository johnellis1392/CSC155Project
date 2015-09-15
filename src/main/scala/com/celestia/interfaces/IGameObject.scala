
package com.celestia.interfaces

import com.jogamp.opengl.GL 

/**
  * Interface for defining a general game object. This is
  * the basemost definition of a game world object, defining 
  * how to move and manipulate an object. 
  */
trait IGameObject {
  def move(deltax:Int, deltay:Int, deltaz:Int):IGameObject
  def draw(gl:GL):Unit 
}

