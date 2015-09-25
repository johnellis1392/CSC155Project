
package com.celestia.services

import com.celestia.interfaces.{IGameWorld, IRenderService}
import com.jogamp.opengl.GLAutoDrawable


/**
  * Class containing logic for rendering the game 
  * world. 
  */
class RenderService extends IRenderService {
  def render(gameWorld:IGameWorld, gl:GLAutoDrawable):Unit={
    gameWorld.gameObjects.foreach((gameObject) => gameObject.render(gl))
  }
}

