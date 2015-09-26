
package com.celestia

import java.io.{FileReader, InputStreamReader, File}
import javax.script.ScriptEngineManager

import com.celestia.util.R
import com.jogamp.opengl._
import org.jruby.embed.jsr223.JRubyScriptEngineManager

object Main {

  //println("Hello, World!")
  //println(System.getProperty("java.library.path"))
  //unsafeAddDir("./lib")
  //println(System.getProperty("java.library.path"))
  //System.loadLibrary("gluegen-rt.jar")
  //System.loadLibrary("libgluegen-rt.so")

  def unsafeAddDir(dir:String)=try {
    val field = classOf[ClassLoader].getDeclaredField("usr_paths")
    field.setAccessible(true)
    val paths = field.get(null).asInstanceOf[Array[String]]
    if(!(paths contains dir)) {
      field.set(null, paths :+ dir)
      System.setProperty("java.library.path",
        System.getProperty("java.library.path") +
          java.io.File.pathSeparator +
          dir) 
    }
  } catch {
    case _: IllegalAccessException => error("Illegal Access; Insufficient Permission")
    case _: NoSuchFieldException => error("JVM Version Incompatible with attempted operation") 
  } 

  def main(args: Array[String]) {
    new MainFrame()
  }
}

