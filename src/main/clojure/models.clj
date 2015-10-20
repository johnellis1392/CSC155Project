
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
	(init [this glAutoDrawable]
		(let [gl (.getGl glAutoDrawable)]
			this))
		
	(update [this time]
		this)
		
	(render [this glAutoDrawable]
		(let [gl (.getGL glAutoDrawable)]
			this))) 



;;;;;;;;;;;;;;;;;;;;;;;;;
; Game State object for holding current game world
(defrecord GameState [camera GameWorld])
  ;IUpdate
  ;IRender
  ;IInit
  
  ;(init [this glAutoDrawable]
    ;(doseq [GameObject (:GameObject this)]
      ;(init GameObject)))
    
  ;(update [this time]
;  	(->GameState 
  		;:camera (:camera this) 
  		;:GameWorld (map update (:GameWorld this))))
  	
  ;(render [this glAutoDrawable]
;  	(doseq [GameObject (:GameObject this)]
  	  ;(render GameObject))))






