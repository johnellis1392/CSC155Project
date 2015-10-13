
(ns game
	(:gen-class))
	
(defrecord Game [mFrame glCanvas]
	GLEventListener
	
	(init [glAutoDrawable]
		(let [gl (.getGL glAutoDrawable)]
			(-> gl
				()))) ; ...
	
	(display [glAutoDrawable]
		(let [gl (.getGL glAutoDrawable)]
			())))
	
	(reshape [glAutoDrawable]
		(let [gl (.getGL glAutoDrawable)]
			()))
	
	(dispose [glAutoDrawable]
		(let [gl (.getGL glAutoDrawable)]
			())))

