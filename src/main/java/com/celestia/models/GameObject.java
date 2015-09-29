
package com.celestia.models;

import com.celestia.interfaces.IRenderStrategy;
import com.celestia.interfaces.IUpdateStrategy;
import graphicslib3D.Matrix3D;

public class GameObject {
    public final Matrix3D position;
    public final Matrix3D rotation; 
    public final Matrix3D scale; 
    
    public final IRenderStrategy renderStrategy;
    public final IUpdateStrategy updateStrategy;
    
    public GameObject(Matrix3D position, Matrix3D rotation, Matrix3D scale, IRenderStrategy renderStrategy, IUpdateStrategy updateStrategy) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.renderStrategy = renderStrategy;
        this.updateStrategy = updateStrategy;
    }
}

