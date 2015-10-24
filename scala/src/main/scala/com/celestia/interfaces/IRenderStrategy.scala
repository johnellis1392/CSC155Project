
package com.celestia.interfaces

import com.jogamp.opengl.GLAutoDrawable

trait IRenderStrategy {
  def render(gameObject:IGameObject, gl:GLAutoDrawable):Unit 
}

