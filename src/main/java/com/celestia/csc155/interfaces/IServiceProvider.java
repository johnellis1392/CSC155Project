package com.celestia.csc155.interfaces;

/**
 * Created by celestia on 9/28/15.
 */
public interface IServiceProvider {
    public IRenderService getRenderService();
    public IUpdateService getUpdateService();
    public ICollisionService getCollisionService();
}
