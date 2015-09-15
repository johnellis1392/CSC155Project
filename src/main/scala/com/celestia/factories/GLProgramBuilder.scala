
package com.celestia.factories

import com.celestia.models.GLProgram
import com.celestia.interfaces.IGLProgramBuilder 

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
  lazy val FRAGMENT_SHADER:Int=0
  lazy val VERTEX_SHADER:Int=1
  lazy val GEOMETRY_SHADER:Int=2
  lazy val NUM_SHADERS:Int=3

  /**
    * List of collected shader objects 
    */
  lazy val shaders:List[Int]=new List[Int](NUM_SHADERS) 

  /**
    * Add a new shader to the current program.
    */
  def addShader(shaderPath:String, shaderType:Int):Int={
    //shaders(shaderType) 
    0 
  }


  /**
    * Construct the new program from the supplied 
    * shaders. 
    */
  def buildProgram:GLProgram={
    new GLProgram(0, 0, 0, 0)  
  } 
}
