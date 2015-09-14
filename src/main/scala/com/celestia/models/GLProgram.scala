
package com.celestia.models


/**
  * Class for holding data to do with GL Programs. 
  * This class holds ID's pointing to a valid GL
  * program, and the shaders that construct it. 
  */
case class GLProgram(
  programId:Int,
  vshaderId:Int,
  fshaderId:Int,
  gshaderId:Int
)

