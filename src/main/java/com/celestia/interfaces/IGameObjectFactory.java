
package com.celestia.interfaces;

public class IGameObjectFactory {
    public Triangle makeTriangle();
    public IGameState initGameState();
    public IGameWorld initGameWorld();
    public ICamera initCamera();
}