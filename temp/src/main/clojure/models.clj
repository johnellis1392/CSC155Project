
(ns models)
  ;(:gen-class))
	
(defprotocol IInit (init [this glAutoDrawable]))
(defprotocol IUpdate (-update [this time]))
(defprotocol IRender (render [this glAutoDrawable]))
(defprotocol IGameObject 
  (translate [this x y z])
  (rotate    [this theta phi radius])
  (scale     [this x y z]))
  


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
(defrecord Camera 
  ;[position rotation zoom]
  [x y z zoom]
  IUpdate
  IRender
  IInit
  IGameObject

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
   this)
   
   (translate
    [this x y z]
    "Move Camera"
    ;(let [position (do (.translate (-> this :position) ) position)
          ;rotation (-> this :rotation)
          ;scale (-> this :scale)]
      ;(->Camera position rotation scale))
      )
         
   (rotate
    [this theta phi radius]
    this)
    
   (scale 
    [this x y z]
    this))
    


























