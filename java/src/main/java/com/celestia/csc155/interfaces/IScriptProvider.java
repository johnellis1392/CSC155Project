package com.celestia.csc155.interfaces;

import javax.script.CompiledScript;
import javax.script.ScriptException;

import com.celestia.csc155.models.GameState;

import java.io.FileNotFoundException;
import java.util.*;

import com.jogamp.opengl.GLAutoDrawable;

/**
 * Created by celestia on 9/28/15.
 */
public interface IScriptProvider {
    IScriptProvider addScript(String path);
    List<CompiledScript> compile()
            throws FileNotFoundException, ScriptException;

    void eval(final GLAutoDrawable glAutoDrawable)
        throws FileNotFoundException, ScriptException;
    
    void eval(final GameState gameState)
    	throws FileNotFoundException, ScriptException;
    
    void eval(final HashMap<String, Object> bindings)
    	throws FileNotFoundException, ScriptException;
    
    void call(String function, Object object)
    	throws FileNotFoundException, ScriptException, NoSuchMethodException ;
}

