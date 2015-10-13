
(ns render 
	(:gen-class))

(defprotocol render class (render [this glAutoDrawable mGameObject]))

