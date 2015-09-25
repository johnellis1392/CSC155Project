
package com.celestia.factories

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
  /*lazy val FRAGMENT_SHADER:Int=0 
  lazy val VERTEX_SHADER:Int=1 
  lazy val GEOMETRY_SHADER:Int=2 
  lazy val NUM_SHADERS:Int=3 

  val vshader:String 
  val fshader:String
  val gshader:String 
   */

  /**
    * Add a new shader to the current program.
    */
  def addShader(shaderPath:String, shaderType:Int):Int=None


  /*def addShader(shaderPath:String, shaderType:Int):Int=shaderType match {
    //shaders(shaderType) 
    case FRAGMENT_SHADER => fshader = shaderPath; 
    case VERTEX_SHADER   => vshader = shaderPath; 
    case GEOMETRY_SHADER => gshader = shaderPath; 
   }*/



  /**
    * Construct the new program from the supplied 
    * shaders. 
    */
  def buildProgram(drawable:GLAutoDrawable):GLProgram={
    // Get GL Context 
    val gl:GL4 = (GL4) drawable.getGL();

    val vshaderId:Int=gl.glCreateShader(GL4.GL_VERTEX_SHADER) 
    val vshaderStream:FileStream=new FileStream(new File(vshader)) 
    gl.glShaderSource(vshaderId, vshaderStream.getSize(), vshaderStream, null)
    gl.glCompileShader(vshaderId); 
  } 
}

