
package com.celestia.csc155;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.script.*;

import clojure.java.api.Clojure;
import clojure.lang.*;
import clojure.lang.Compiler;

//import com.celestia.csc155.factories.GLProgramBuilder;

public class Main {
    public static void main(String[] args) {
//        new MainFrame();
    	try {
    		IFn require = Clojure.var("clojure.core", "require");
//    		RT.loadResourceScript("src/main/clojure/com/celestia/main.clj");
//    		require.invoke(Clojure.read("com.celestia.main"));
//    		IFn function = Clojure.var("com.celestia.main", "test");
    		
    		// GOT IT TO WORK WOOO
//    		RT.loadResourceScript("src/main/clojure/init.clj");
    		RT.loadResourceScript("src/main/clojure/main.clj");
//    		RT.loadResourceScript("src/main/clojure/render.clj");
//    		RT.loadResourceScript("src/main/clojure/update.clj");
    		RT.loadResourceScript("src/main/clojure/util.clj");
    		RT.loadResourceScript("src/main/clojure/models.clj");
    		require.invoke(Clojure.read("main-ns"));
//    		require.invoke(Clojure.read("init"));
    		IFn function = Clojure.var("main-ns", "main");
	    	function.invoke();
	    	
//	    	ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
//	    	ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("jruby");
//	    	scriptEngine.eval("puts 'Hello, World!'");
	    	
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
}

