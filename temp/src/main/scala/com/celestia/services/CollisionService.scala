
package com.celestia.services

import com.celestia.interfaces.{IGameObject, IGameWorld, ICollisionService}

/**
  * Service class for handling collision events 
  * between game objects. The actual collision 
  * logic is delegated to the collision handler
  * strategy object in the game objects 
  */
class CollisionService extends ICollisionService {

  /**
    * Detect collisions between all pairs of objects 
    * contained in the game world. 
    */
  override def detectCollisions(gameWorld:IGameWorld):IGameWorld={
    new IGameWorld {
      override def gameObjects: List[IGameObject] = List()
    }
  }
}


