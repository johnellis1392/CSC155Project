
(import com.jogamp.opengl.GLEventListener)
(import com.celestia.models)
(ns com.celestia.util
	(:gen-class)
	(:require []))

(defonce mGameState (GameState.))

;; Event Handler class 
(defrecord GLEventHandler []
    GLEventListener ; Implement Event Listener Interface
    (defn init [glAutoDrawable] 
        ())
    
;    (defn display [glAutoDrawable] 
;        (reset! mGameState (update))
;        (render mGameState))

	; Update Game State
	(defn display [glAutoDrawable]
		(->> mGameState 
			(update)
			(render)
			(reset! mGameState)))
    
    (defn reshape [glAutoDrawable] 
        ())
    
    (defn dispose [glAutoDrawable] 
        ()))






; (gen-class 
;     :name com.celestia.util.GLEventHandler
;     :implements com.jogamp.opengl.GLEventListener
    
;     (defn -init [] ())
    
;     (defn -display [glAutoDrawable] 
;         (let [[gl (.getGL glAutoDrawable)]]
;             (loop for gameObject in mGameState
;                   (do 
;                       (.update gameObject)))
                  
;             (loop for gameObject in mGameState
;                   (do 
;                       (.detectCollisions gameObject)))
                  
;             (loop for gameObject in mGameState 
;                   (do 
;                       (.render gameObject)))))
    
;     (defn -dispose [glAutoDrawable] 
;         (let [[gl (.getGL glAutoDrawable)]]
;              ()))
    
;     (defn -reshape [glAutoDrawable] 
;         (let [[gl (.getGL glAutoDrawable]] 
;               ()))))

