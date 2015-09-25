
package com.celestia.services

import com.celestia.interfaces.{IGameWorld, ICollisionService}

/**
  * Service class for handling collision events 
  * between game objects. The actual collision 
  * logic is delegated to the collision handler
  * strategy object in the game objects 
  */
class CollisionService extends ICollisionService {
  /*override def collide(g1:IGameObject, g2:IGameObject):Unit={
    g1.collide(g2)
    g2.collide(g1) 
   }*/

  /**
    * Detect collisions between all pairs of objects 
    * contained in the game world. 
    */
  override def detectCollisions(gameWorld:IGameWorld):IGameWorld={
    None 
  }
}


