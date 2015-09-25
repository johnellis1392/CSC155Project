
package com.celestia.interfaces

trait IServiceProvider {
  def getRenderService:IRenderService
  def getUpdateService:IUpdateService
  def getCollisionService:ICollisionService
}