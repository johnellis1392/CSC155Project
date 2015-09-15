
package com.celestia.interfaces

import com.celestia.models.GLProgram

/**
  * Interface for creating a GL Program Builder class. 
  * This interface defines how the program will interact
  * with GL Programs; by adding a collection of shaders
  * to a program and compiling them on the GPU. 
  */
trait IGLProgramBuilder {
  def addShader(shaderPath:String, shaderType:Int):Int
  def buildProgram():GLProgram
}
