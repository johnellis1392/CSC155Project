
package com.celestia.interfaces

import com.jogamp.opengl.{GL3, GL2ES2, GL4, GLAutoDrawable}
import com.celestia.models.GLProgram

/**
  * Interface for creating a GL Program Builder class. 
  * This interface defines how the program will interact
  * with GL Programs; by adding a collection of shaders
  * to a program and compiling them on the GPU. 
  */
trait IGLProgramBuilder {
  lazy val VERTEX_SHADER:Int = GL2ES2.GL_VERTEX_SHADER
  lazy val FRAGMENT_SHADER:Int = GL2ES2.GL_FRAGMENT_SHADER
  lazy val GEOMETRY_SHADER:Int = GL3.GL_GEOMETRY_SHADER

  def addShader(shaderPath:String, shaderType:Int):IGLProgramBuilder
  def build(gl:GLAutoDrawable):GLProgram
}
