
package com.celestia.factories

import com.celestia.interfaces._
import com.celestia.models.{Camera, GameWorld, GameState, Triangle}
import com.celestia.strategies.{RenderStrategy, UpdateStrategy}
import graphicslib3D.Matrix3D


/**
 * Singleton Factory class for creating new Game
 * objects.
 */
object GameObjectFactory extends IGameObjectFactory {

  /**
   * Create a new Triangle object
   * @return
   */
  override def makeTriangle:Triangle = {
    new Triangle(
      new Matrix3D(),
      new Matrix3D(),
      new Matrix3D(),
      new UpdateStrategy(),
      new RenderStrategy()
    )
  }

  /**
   * Create  new Game State object
   * @return
   */
  override def initGameState:IGameState={
    new GameState(
      initGameWorld,
      initCamera
    )
  }


  /**
   * Create a new Game World collection
   * @return
   */
  override def initGameWorld:IGameWorld=new GameWorld(List[IGameObject]())


  /**
   * Create a new Camera object
   * @return
   */
  override def initCamera:ICamera=new Camera(
    0,
    0,
    0
  )
//  override def initCamera:ICamera=new Camera(
//    new Matrix3D(),
//    new Matrix3D(),
//    new Matrix3D()
//  )
}