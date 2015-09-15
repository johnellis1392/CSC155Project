
package com.celestia

import java.awt.Dimension
import java.awt.BorderLayout 
import javax.swing.JFrame 
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.awt.GLCanvas 
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

  // General variables to do with JFrame dimensions 
  lazy val aspectRatio: Double = 9.0 / 16.0 
  lazy val defaultWidth: Int = 1000
  lazy val defaultHeight: Int = (defaultWidth * aspectRatio).toInt 
  lazy val Size: Dimension = new Dimension(defaultWidth, defaultHeight)

  // GLCanvas object for rendering
  lazy val glCanvas: GLCanvas = new GLCanvas 

  /**
    * Initializer for creating the base JFrame object 
    */
  def setup {
    println("Initializing GL Frame...")
    setTitle("Test Panel") 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setLayout(new BorderLayout)
    add(glCanvas) 
    setSize(Size) 
    setLocationRelativeTo(null) 
    setVisible(true) 
  }

  override def display(drawable:GLAutoDrawable):Unit={}
  override def init(drawable:GLAutoDrawable):Unit={}
  override def reshape(drawable:GLAutoDrawable, x:Int, y:Int, width:Int, height:Int):Unit={}
  override def dispose(drawable:GLAutoDrawable):Unit={}

  // Call initializer function 
  setup
}
