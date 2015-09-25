
package com.celestia.services

import java.nio.FloatBuffer

import com.celestia.interfaces.{IGameWorld, IRenderService}
import com.jogamp.opengl._
import com.jogamp.opengl.GL4._


/**
  * Class containing logic for rendering the game 
  * world. 
  */
class RenderService extends IRenderService {
  def render(gameWorld:IGameWorld, glAutoDrawable:GLAutoDrawable):Unit={
    val gl:GL4 = glAutoDrawable.getGL.asInstanceOf[GL4]
    val color = FloatBuffer.allocate(4)
    color.put(0, 0.5f)
    color.put(1, 0.5f)
    color.put(2, 0.5f)
    color.put(3, 0.5f)
//    gl.glClear(GL4.GL_DEPTH_BUFFER_BIT)
//    gl.glClearDepthf(GL4.GL_COLOR)
//    gl.glUseProgram(glProgram.programId)
    gameWorld.gameObjects.foreach((gameObject) => gameObject.render(glAutoDrawable))
  }
}

