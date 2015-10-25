
package com.celestia.interfaces

import javax.script.CompiledScript

/**
 * Interface for providing script compilation
 * functions
 */
trait IScriptProvider {
  def addScript(path:String):IScriptProvider
  def compileScripts:List[CompiledScript]
}