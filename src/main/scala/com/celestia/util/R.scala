
package com.celestia.util

import com.jogamp.opengl.{GL3, GL2ES2, GL2}

/**
  * Static Singleton object for storing directory
  * names and relevant project resources. 
  */
object R {
  lazy val ResourcePath:String = "./src/main/res/"

  object shaders {
    lazy val ShaderLocation:String = ResourcePath + "shaders/"
    lazy val FragmentShader:String = ShaderLocation + "fshader.glsl"
    lazy val VertexShader:String = ShaderLocation + "vshader.glsl"
    lazy val GeometryShader:String = ShaderLocation + "gshader.glsl"
  }

  object ruby {
    lazy val RubyLocation:String = ResourcePath + "ruby/"
    lazy val main:String = RubyLocation + "main.rb"
  }

  object shader_types {
    lazy val VERTEX_SHADER:Int = GL2ES2.GL_VERTEX_SHADER
    lazy val FRAGMENT_SHADER:Int = GL2ES2.GL_FRAGMENT_SHADER
    lazy val GEOMETRY_SHADER:Int = GL3.GL_GEOMETRY_SHADER
  }

  object vertices {
//    lazy val triangle
  }
}

