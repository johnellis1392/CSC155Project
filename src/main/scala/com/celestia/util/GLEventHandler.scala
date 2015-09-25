package com.celestia.util

import com.celestia.factories.GLProgramBuilder
import com.celestia.interfaces.IGLProgramBuilder
import com.jogamp.opengl.{GLAutoDrawable, GLEventListener}
import com.celestia.models.GLProgram

/**
 * Created by celestia on 9/24/15.
 */
class GLEventHandler extends GLEventListener {

  // Values for holding the state of the game
  private lazy val glProgramBuilder:IGLProgramBuilder = new GLProgramBuilder
  private var glProgram:GLProgram = new GLProgram(0, 0, 0, 0)


  /**
   * Function for handling initialization of the GL components
   * of the game
   * @param glAutoDrawable
   */
  override def init(glAutoDrawable: GLAutoDrawable): Unit = {
    glProgramBuilder.addShader(R.shaders.FragmentShader, 0)
    glProgramBuilder.addShader(R.shaders.VertexShader, 1)
    glProgram = glProgramBuilder.buildProgram()

  }


  /**
   * Function for rendering the game world
   * @param glAutoDrawable
   */
  override def display(glAutoDrawable: GLAutoDrawable): Unit = {

  }


  /**
   * Function for reshaping the frame
   * @param glAutoDrawable
   * @param i
   * @param i1
   * @param i2
   * @param i3
   */
  override def reshape(glAutoDrawable: GLAutoDrawable, i: Int, i1: Int, i2: Int, i3: Int): Unit = {

  }


  /**
   * Function for disposing of the gl components
   * @param glAutoDrawable
   */
  override def dispose(glAutoDrawable: GLAutoDrawable): Unit = {

  }

}
