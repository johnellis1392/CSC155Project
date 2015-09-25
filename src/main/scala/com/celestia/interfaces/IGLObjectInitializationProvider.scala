package com.celestia.interfaces

import com.celestia.models.Triangle

/**
 * Created by celestia on 9/24/15.
 */
trait IGLObjectInitializationProvider {
  def initTriangle(gameObject:Triangle)
}
