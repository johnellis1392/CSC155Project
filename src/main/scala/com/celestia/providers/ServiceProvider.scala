package com.celestia.providers

import com.celestia.interfaces.{ICollisionService, IUpdateService, IRenderService, IServiceProvider}
import com.celestia.services.{CollisionService, UpdateService, RenderService}

/**
 * Created by celestia on 9/24/15.
 */
class ServiceProvider extends IServiceProvider {

  /**
   * Create a new RenderService class
   * @return
   */
  override def getRenderService:IRenderService={
    new RenderService
  }


  /**
   * Create a new UpdateService
   * @return
   */
  override def getUpdateService:IUpdateService={
    new UpdateService
  }


  /**
   * Create a new CollisionService
   * @return
   */
  override def getCollisionService:ICollisionService={
    new CollisionService
  }
}
