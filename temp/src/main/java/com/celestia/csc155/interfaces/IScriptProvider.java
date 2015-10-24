package com.celestia.csc155.interfaces;

import javax.script.CompiledScript;
import javax.script.ScriptException;

import com.celestia.csc155.models.GameState;

import java.io.FileNotFoundException;
import java.util.List;

import com.jogamp.opengl.GLAutoDrawable;

/**
 * Created by celestia on 9/28/15.
 */
public interface IScriptProvider {
    IScriptProvider addScript(String path);
    List<CompiledScript> compile()
            throws FileNotFoundException, ScriptException;

    void eval(GLAutoDrawable glAutoDrawable)
        throws FileNotFoundException, ScriptException;
    
    void eval(GameState gameState)
    	throws FileNotFoundException, ScriptException;
    
    void call(String function, Object object)
    	throws FileNotFoundException, ScriptException, NoSuchMethodException ;
}
