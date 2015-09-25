
package com.celestia.factories

import java.io.File
import java.util

import com.celestia.models.GLProgram
import com.celestia.interfaces.IGLProgramBuilder
import com.jogamp.opengl.GL2._ 
import com.jogamp.opengl._ 
import com.jogamp.opengl.awt.GLCanvas

/**
 * Builder class for constructing a valid GL Program
 * from multiple shader obejcts. This class can take
 * any number of shaders and will add them to the
 * private program object. After all shaders have
 * been added, the class can be told to iterate through
 * the shader collection and construct a new GLProgram
 * from the shader collection.
 */
class GLProgramBuilder(val shaders: List[(String, Int)]=List()) extends IGLProgramBuilder {

  /**
   * Add a new shader to the current program.
   */
  override def addShader(shaderPath:String, shaderType:Int):IGLProgramBuilder = new GLProgramBuilder((shaderPath, shaderType) :: shaders)


  /**
   * Construct the new program from the supplied
   * shaders.
   */
  override def build(drawable:GLAutoDrawable):GLProgram={
    // Get GL Context 
    val gl:GL4 = drawable.getGL.asInstanceOf[GL4];
    val programId:Int = gl.glCreateProgram()

    // Iterate through shader list and compile
    // all shader sources
    val shaderIds = shaders.foldLeft(List[Int]())((list, pair) => {
      val shaderSource:Array[String] = io.Source.fromFile(pair._1).map((i)=>i.toString).toArray
      val lineLengths:Array[Int] = shaderSource.map((i)=>i.length)
      val shaderId = gl.glCreateShader(pair._2)
      gl.glShaderSource(shaderId, shaderSource.length, shaderSource, lineLengths, 0)
      gl.glCompileShader(shaderId)
      gl.glAttachShader(programId, shaderId)
      shaderId :: list
    })

    // Link the program and return a new GLProgram
    // wrapper object
    gl.glLinkProgram(programId)
    shaderIds.foreach((i)=>gl.glDeleteShader(i))
    new GLProgram(programId, shaderIds)
  }
}

