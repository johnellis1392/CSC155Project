
package com.celestia.util

import com.jogamp.opengl._

/**
  * Static Singleton object for storing directory
  * names and relevant project resources. 
  */
object R {
  lazy val ResourcePath:String = "./src/main/res/"

  // Location of shaders
  object shaders {
    lazy val ShaderLocation:String = ResourcePath + "shaders/"
    lazy val FragmentShader:String = ShaderLocation + "fshader.glsl"
    lazy val VertexShader:String = ShaderLocation + "vshader.glsl"
    lazy val GeometryShader:String = ShaderLocation + "gshader.glsl"
  }

  // Locations of scripts
  object ruby {
    lazy val RubyLocation:String = ResourcePath + "ruby/"
    lazy val main:String = RubyLocation + "main.rb"
  }

  // GL Configuration Constants
  object gl {
    object shaders {
      lazy val VERTEX_SHADER:Int = GL2ES2.GL_VERTEX_SHADER
      lazy val FRAGMENT_SHADER:Int = GL2ES2.GL_FRAGMENT_SHADER
      lazy val GEOMETRY_SHADER:Int = GL3.GL_GEOMETRY_SHADER
    }

    lazy val GL_COLOR:Int = GL2ES3.GL_COLOR
    lazy val GL_COLOR_BUFFER_BIT:Int = GL.GL_COLOR_BUFFER_BIT
    lazy val GL_DEPTH_BUFFER_BIT:Int = GL.GL_DEPTH_BUFFER_BIT
    lazy val GL_TRIANGLES:Int = GL.GL_TRIANGLES
  }

  // Vertices for game objects
  object vertices {
//    lazy val triangle
  }

  // Miscellaneous
  object util {
    lazy val title:String = "John Ellis - A1"
    lazy val fps: Int = 50
  }
}

