
(ns models
  (:gen-class))
	
(defprotocol IInit (init [this glAutoDrawable]))
(defprotocol IUpdate (update [this time]))
(defprotocol IRender (render [this glAutoDrawable]))


;;;;;;;;;;;;;;;;;;;;;;;;;
; Generic Triangle
(defrecord Triangle [position rotation scale]
  IUpdate
  IRender
  IInit
  
  (init
   [this glAutoDrawable]
   (let [gl (.getGl glAutoDrawable)]
     this))
		
  (update
   [this time]
   (println "Camera: " (:position this) (:rotation this) (:zoom this))
   this)
		
  (render
   [this glAutoDrawable]
   (let [gl (.getGL glAutoDrawable)]
     this))) 


;;;;;;;;;;;;;;;;;;;;;;;;;
; Camera object for storing View data
(defrecord Camera [position rotation zoom]
  IUpdate
  IRender
  IInit

  (init
   [this glAutoDrawable]
   "Initialize the Camera object"
   this)

  (update
   [this glAutoDrawable]
   "Update the Camera Position"
   (println "Camera: " (:position this) (:rotation this) (:zoom this))
   this)

  (render
   [this glAutoDrawable]
   "Render the Camera???"
   this))


;;;;;;;;;;;;;;;;;;;;;;;;;
; Game State object for holding current game world
;(defrecord GameState [camera gameWorld]
;  IUpdate
;  IRender
;  IInit
;  
;  (init
;   [this glAutoDrawable]
;   (doseq [gameObject (:gameWorld this)]
;     (init gameObject)))
;    
;  (update
;   [this time]
;   (->GameState 
;    :camera (:camera this) 
;    :gameWorld (map update (:gameWorld this))))
;  	
;  (render
;   [this glAutoDrawable]
;   (doseq [gameObject (:gameObject this)]
;     (render gameObject))))


