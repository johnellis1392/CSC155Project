
package com.celestia

import javax.swing.JFrame 
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.GLAutoDrawable 
//import com.jogamp.opengl.GLCanvas

/**
  * Main Frame class
  * 
  * This class is the primary Frame for handling GL drawing.
  * It contains a GLEventListener implementation for handling
  * user input, and a GLCanvas object for drawing to the 
  * graphics card.
  */
class MainFrame extends JFrame with GLEventListener {

  def setup {
    println("Initializing GL Frame...")
    setTitle("Test Panel") 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setVisible(true) 
  }

  override def display(drawable:GLAutoDrawable):Unit={}
  override def init(drawable:GLAutoDrawable):Unit={}
  override def reshape(drawable:GLAutoDrawable, x:Int, y:Int, width:Int, height:Int):Unit={}
  override def dispose(drawable:GLAutoDrawable):Unit={}

  // Call initializer function 
  setup
}
