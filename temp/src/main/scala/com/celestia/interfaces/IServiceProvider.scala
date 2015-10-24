
package com.celestia.interfaces

import com.celestia.models.GLProgram

trait IServiceProvider {
  def getRenderService:IRenderService
  def getUpdateService:IUpdateService
  def getCollisionService:ICollisionService
}