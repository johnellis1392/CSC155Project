package com.celestia.csc155.providers;

import com.celestia.csc155.interfaces.IScriptProvider;
import com.celestia.csc155.models.GameState;

import javax.script.*;

import java.io.*;
import java.util.*;

import com.jogamp.opengl.*;

/**
 * Created by celestia on 9/28/15.
 */
public class ScriptProvider implements IScriptProvider {

	public static final String engine = "jruby";
    private final ArrayList<String> scripts;

    
    @Override
    public void eval(final HashMap<String, Object> hashMap) 
    	throws FileNotFoundException, ScriptException {
    	final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    	final ScriptEngine ruby = scriptEngineManager.getEngineByName(engine);
    	final Bindings bindings = ruby.getBindings(ScriptContext.ENGINE_SCOPE);
    	for(Map.Entry<String, Object> entry : hashMap.entrySet()) {
    		bindings.put(
    			entry.getKey(), 
    			entry.getValue()
    		);
    	}
    	
    	for(String script : scripts) {
    		final FileReader fileReader = new FileReader(script);
    		ruby.eval(fileReader);
    	}
    }
    

    @Override
    public void eval (final GLAutoDrawable glAutoDrawable)
        throws FileNotFoundException, ScriptException {
        final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        final ScriptEngine ruby = scriptEngineManager.getEngineByName(engine);
//        Bindings bindings = ruby.getBindings(ScriptContext.ENGINE_SCOPE);
//        bindings.put("gl", (GL4) glAutoDrawable.getGL());
//        bindings.put("GL_DEPTH_BUFFER_BIT", GL4.GL_DEPTH_BUFFER_BIT);
//        bindings.put("GL_TRIANGLES", GL4.GL_TRIANGLES);
//        bindings.put("GL_COLOR", GL4.GL_COLOR); 
        
        for(String script : scripts) {
            final FileReader fileReader = new FileReader(script);
            ruby.eval(fileReader);
        }
    }
    
    
    /**
     * Evaluate all scripts in collection
     */
    @SuppressWarnings("restriction")
	public void eval(final GameState gameState) 
    		throws FileNotFoundException, ScriptException {
    	
    	final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    	final ScriptEngine ruby = scriptEngineManager.getEngineByName(engine);
    	
    	for(String script : scripts) {
            final FileReader fileReader = new FileReader(script);
            ruby.eval(fileReader);
    	}
    }
    
    
    /**
     * Compile all scripts in collection
     */
    @SuppressWarnings("restriction")
    public List<CompiledScript> compile()
            throws FileNotFoundException, ScriptException {

        ArrayList<CompiledScript> compiledScripts = new ArrayList<CompiledScript>();
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine ruby = scriptEngineManager.getEngineByName(engine);
        Compilable compiler = (Compilable) ruby;

        for(String script : scripts) {
            FileReader fileReader = new FileReader(script);
            compiledScripts.add(compiler.compile(fileReader));
        }

        return compiledScripts;
    }
    
    
    /**
     * Call a function in the script collection 
     */
    @SuppressWarnings("restriction")
	public void call(String function, Object object) 
    		throws FileNotFoundException, ScriptException, NoSuchMethodException {
    	ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    	ScriptEngine ruby = scriptEngineManager.getEngineByName(engine);
    	
    	for(String script : scripts) {
    		FileReader fileReader = new FileReader(script);
    		ruby.eval(fileReader);
    		Invocable invocable = (Invocable) ruby;
    		invocable.invokeFunction(function, object);
    	}
    }

    
    public IScriptProvider addScript(final String path) {
        ArrayList<String> list = new ArrayList<String>(scripts);
        list.add(path);
        return new ScriptProvider(list);
    }

    public ScriptProvider() {
        this.scripts = new ArrayList<String>();
    }

    private ScriptProvider(final ArrayList<String> scripts) {
        this.scripts = scripts;
    }
}
