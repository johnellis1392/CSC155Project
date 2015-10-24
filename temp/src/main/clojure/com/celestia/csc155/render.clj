
(ns com.celestia.csc155.render 
  (:gen-class))

(defprotocol render class (render [this glAutoDrawable mGameObject]))

