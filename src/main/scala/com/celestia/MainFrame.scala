
package com.celestia

import java.awt.Dimension
import java.awt.BorderLayout 
import javax.swing.JFrame

import com.jogamp.opengl.util.FPSAnimator

//import com.jogamp.opengl.GLEventListener
//import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.awt.GLCanvas

import com.celestia.util.R
import com.celestia.util.GLEventHandler


/**
  * Main Frame class
  * 
  * This class is the primary Frame for handling GL drawing.
  * It contains a GLEventListener implementation for handling
  * user input, and a GLCanvas object for drawing to the 
  * graphics card.
  */
class MainFrame extends JFrame {

  // General variables to do with JFrame dimensions 
  lazy val aspectRatio: Double = 9.0 / 16.0 
  lazy val defaultWidth: Int = 1000
  lazy val defaultHeight: Int = (defaultWidth * aspectRatio).toInt 
  lazy val Size: Dimension = new Dimension(defaultWidth, defaultHeight)
  lazy val fps: Int = 50

  // GLCanvas object for rendering
  lazy val glEVentHandler: GLEventHandler = new GLEventHandler
  lazy val glCanvas: GLCanvas = new GLCanvas
  lazy val fpsAnimator:FPSAnimator = new FPSAnimator(50)

  /**
   * Initializer for creating the base JFrame object
   */
  def setup {
    println("Initializing GL Frame...")
    setTitle("Test Panel") 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setLayout(new BorderLayout)
    glCanvas.addGLEventListener(glEVentHandler)
    glCanvas.setAnimator(fpsAnimator)
    add(glCanvas, BorderLayout.CENTER)
    setSize(Size) 
    setLocationRelativeTo(null) 
    setVisible(true)
    fpsAnimator.start()
  }

  // Call initializer function 
  setup
}
