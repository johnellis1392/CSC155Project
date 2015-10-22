
package com.celestia.csc155;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.script.*;

import clojure.java.api.Clojure;
import clojure.lang.*;
import clojure.lang.Compiler;


public class Main {
    public static void main(String[] args) {
    	try {
            //IFn require = Clojure.var("clojure.core", "require");
            RT.loadResourceScript("src/main/clojure/main.clj");
            RT.loadResourceScript("src/main/clojure/util.clj");
            RT.loadResourceScript("src/main/clojure/models.clj");
            
            require.invoke(Clojure.read("main-ns"));
            IFn function = Clojure.var("main-ns", "main");
            function.invoke();
	    
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}

