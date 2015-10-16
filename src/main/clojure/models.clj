
(ns models
	(:gen-class))
	
(defprotocol IInit (init [this glAutoDrawable]))
(defprotocol IUpdate (update [this time]))
(defprotocol IRender (render [this glAutoDrawable]))

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

