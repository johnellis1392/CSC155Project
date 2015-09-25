
package com.celestia.util

/**
  * Static Singleton object for storing directory
  * names and relevant project resources. 
  */
object R {
  lazy val ResourcePath:String = "src/main/res/"
  object shaders {
    lazy val ShaderLocation:String = ResourcePath + "shaders/"
    lazy val FragmentShader:String = ShaderLocation + "fshader.glsl"
    lazy val VertexShader:String = ShaderLocation + "vshader.glsl"
    lazy val GeometryShader:String = ShaderLocation + "gshader.glsl"
  }
}

