
package com.celestia.util

/**
  * Static Singleton object for storing directory
  * names and relevant project resources. 
  */
object Resources {
  lazy val ResourcePath:String = "src/main/res/"
  lazy val FragmentShader:String = ResourcePath + "fshader.glsl" 
  lazy val VertexShader:String = ResourcePath + "vshader.glsl" 
  lazy val GeometryShader:String = ResourcePath + "gshader.glsl" 
}

