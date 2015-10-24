
package com.celestia.interfaces

import com.jogamp.opengl.GLAutoDrawable

trait IRenderService {
  def render(gameState:IGameState, gl:GLAutoDrawable):Unit
}

