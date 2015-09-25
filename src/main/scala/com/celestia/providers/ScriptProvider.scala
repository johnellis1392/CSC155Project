
package com.celestia.providers

import java.io.FileReader
import javax.script.{Compilable, CompiledScript}

import com.celestia.interfaces.IScriptProvider
import org.jruby.embed.jsr223.JRubyScriptEngineManager

class ScriptProvider(scripts:List[String]) extends IScriptProvider {

  /**
   * Add a new script path to the list of scripts
   * @param path
   * @return
   */
  override def addScript(path:String):IScriptProvider={
    new ScriptProvider(path :: scripts)
  }


  /**
   * Compile all scripts into a list of compiled
   * scripts to return to the program
   * @return
   */
  override def compileScripts:List[CompiledScript]={
    val factory = new JRubyScriptEngineManager()
    val ruby = factory.getEngineByName("jruby")
    scripts.map((path) => {
      val fileReader = new FileReader(path)
      ruby.asInstanceOf[Compilable].compile(fileReader)
    })
  }
}