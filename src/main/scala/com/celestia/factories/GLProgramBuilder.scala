
package com.celestia.factories

import com.celestia.models.GLProgram 

/**
  * Builder class for constructing a valid GL Program
  * from multiple shader obejcts. This class can take
  * any number of shaders and will add them to the 
  * private program object. After all shaders have
  * been added, the class can be told to iterate through
  * the shader collection and construct a new GLProgram
  * from the shader collection.
  */
class GLProgramBuilder {


  /**
    * Add a new shader to the current program.
    */
  def addShader(shaderPath:String, shaderType:Int):Int={
    0
  }


  /**
    * Construct the new program from the supplied 
    * shaders. 
    */
  //def buildProgram:GLProgram={
  //  None 
  //} 
}
