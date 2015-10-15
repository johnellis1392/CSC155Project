
(ns main-ns
	(:gen-class)
	(:require [init :refer :all]))

(require '[render :refer :all])
(require '[update :as u])

;(import javax.swing.JFrame)
;(import com.jogamp.opengl.awt.GLCanvas)

;(def fps 60)
;(def frame (JFrame.)) 
;(def glCanvas (GLCanvas.)) 
;(def fpsAnimator (FPSAnimator. fps)) 
;(def glEventHandler (GLEventHandler.)) 

(defn main [& args] 
	(println "Blah some more")
	(init)
	(render)
	(u/update))

