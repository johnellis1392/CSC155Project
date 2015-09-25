
package com.celestia.interfaces

import com.jogamp.opengl.GLAutoDrawable

trait IRenderService {
  def render(gameWorld:IGameWorld, gl:GLAutoDrawable):Unit
}

