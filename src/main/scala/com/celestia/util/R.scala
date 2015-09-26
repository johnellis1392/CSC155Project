
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
    lazy val TessellationEvaluationShader:String = ShaderLocation + "teshader.glsl"
    lazy val TessellationControlShader:String = "tcshader.glsl"
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

    object uniforms {
      lazy val model_view_matrix:String = "model_view_matrix"
      lazy val projection_matrix:String = "projection_matrix"
    }

    lazy val GL_COLOR:Int = GL2ES3.GL_COLOR
    lazy val GL_COLOR_BUFFER_BIT:Int = GL.GL_COLOR_BUFFER_BIT
    lazy val GL_DEPTH_BUFFER_BIT:Int = GL.GL_DEPTH_BUFFER_BIT
    lazy val GL_TRIANGLES:Int = GL.GL_TRIANGLES
    lazy val GL_CULL_FACE:Int = GL.GL_CULL_FACE
    lazy val GL_CW:Int = GL.GL_CW
    lazy val GL_DEPTH_TEST:Int = GL.GL_DEPTH_TEST
    lazy val GL_LEQUAL:Int = GL.GL_LEQUAL

    lazy val GL_ARRAY_BUFFER:Int = GL.GL_ARRAY_BUFFER
    lazy val GL_POINTS:Int = GL.GL_POINTS
    lazy val GL_FLOAT:Int = GL.GL_FLOAT
  }

  // Vertices for game objects
  object vertices {
//    lazy val triangle
  }

  // Miscellaneous
  object util {
    lazy val title:String = "John Ellis - A1"
    lazy val fps: Int = 50
    lazy val aspect:Double = 16.0 / 9.0
    lazy val width:Int = 1000
    lazy val height:Int = (width / aspect).toInt
  }
}

