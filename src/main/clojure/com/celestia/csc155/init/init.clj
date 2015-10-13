
(ns init 
	(:gen-class))

(defprotocol init class (init [this]))

(defn init [] 
  (println "Hello, World!"))

(defn t []
  "Test String")
  
  