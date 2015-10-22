
(ns models)
  ;(:gen-class))
	
(defprotocol IInit (init [this glAutoDrawable]))
(defprotocol IUpdate (-update [this time]))
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
		
  (-update
   [this time]
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

  (-update
   [this time]
   this)

  (render
   [this glAutoDrawable]
   "Render the Camera???"
   this))


