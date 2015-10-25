
package com.celestia.csc155.interfaces;

import com.celestia.csc155.models.GameObject;

public interface ICollisionStrategy {
    GameObject collide(GameObject g1, GameObject g2);
}