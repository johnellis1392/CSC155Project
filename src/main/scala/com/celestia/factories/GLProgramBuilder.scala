
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
class GLProgramBuilder extends IGLProgramBuilder {

  /**
   * Constants for setting shaders
   */
  private val shaders:util.ArrayList[(String, Int)] = new util.ArrayList[(String, Int)]


  /**
   * Add a new shader to the current program.
   */
  override def addShader(shaderPath:String, shaderType:Int):IGLProgramBuilder={
    shaders.add((shaderPath, shaderType))
    this
  }


  /**
   * Construct the new program from the supplied
   * shaders.
   */
  override def build(drawable:GLAutoDrawable):GLProgram={
    // Get GL Context 
    val gl:GL = drawable.getGL();

//    val vshaderId:Int=gl.glCreateShader(GL4.GL_VERTEX_SHADER)
//    val vshaderStream:FileStream=new FileStream(new File(vshader))
//    gl.glShaderSource(vshaderId, vshaderStream.getSize(), vshaderStream, null)
//    gl.glCompileShader(vshaderId);
    new GLProgram(0,0,0,0)
  } 
}

