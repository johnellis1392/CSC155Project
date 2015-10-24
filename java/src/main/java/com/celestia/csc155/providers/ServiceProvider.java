package com.celestia.csc155.providers;

import com.celestia.csc155.interfaces.ICollisionService;
import com.celestia.csc155.interfaces.IRenderService;
import com.celestia.csc155.interfaces.IServiceProvider;
import com.celestia.csc155.interfaces.IUpdateService;
import com.celestia.csc155.models.GameState;
import com.celestia.csc155.services.CollisionService;
import com.celestia.csc155.services.RenderService;
import com.celestia.csc155.services.UpdateService;

/**
 * Created by celestia on 9/28/15.
 */
public class ServiceProvider implements IServiceProvider {
    
    public IUpdateService getUpdateService() {
        return new UpdateService();
    }

    
    public IRenderService getRenderService() {
        return new RenderService();
    }

    
    public ICollisionService getCollisionService() {
        return new CollisionService();
    }
}
