
package com.celestia.interfaces

import com.celestia.models.Triangle

trait IGameObjectFactory {
  def makeTriangle:Triangle
  def initGameState:IGameState
  def initCamera:ICamera
  def initGameWorld:IGameWorld
}

