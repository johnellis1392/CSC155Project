
package com.celestia.services

import java.nio.FloatBuffer

import com.celestia.interfaces.{IGameState, IRenderService}
import com.celestia.util.R
import com.jogamp.opengl._
import com.jogamp.opengl.GL4


/**
  * Class containing logic for rendering the game 
  * world. 
  */
class RenderService() extends IRenderService {
  override def render(gameState:IGameState, glAutoDrawable:GLAutoDrawable):Unit={
    val gl:GL4 = glAutoDrawable.getGL.asInstanceOf[GL4]
    val color = FloatBuffer.allocate(4)
    color.put(0, 0.5f)
    color.put(1, 0.5f)
    color.put(2, 0.5f)
    color.put(3, 0.5f)
    gl.glClear(R.gl.GL_DEPTH_BUFFER_BIT)
    gl.glClearBufferfv(R.gl.GL_COLOR, 0, color)
    gameState.gameWorld.gameObjects.foreach((gameObject) => gameObject.render(glAutoDrawable))
  }
}

