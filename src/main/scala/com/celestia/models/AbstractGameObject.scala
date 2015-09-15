
package com.celestia.models

import com.jogamp.opengl.GL 
import com.celestia.interfaces.IGameObject 

/**
  * Abstract implementation of a Game Object. This is
  * the original base definition of a Game Object. it 
  * defines a position object, and initial accessors and
  * mutators for model data. 
  */
abstract class AbstractGameObject extends IGameObject {
  //var position:Point = new Point(0, 0, 0) 
  def move(deltax:Int, deltay:Int, deltaz:Int):IGameObject={
    this 
  }

  def draw(gl:GL):Unit={

  }
}

