
(ns main-ns
  (:require [util :as util]
            [models :as models]))

(import javax.swing.JFrame)
(import com.jogamp.opengl.awt.GLCanvas)
(import com.jogamp.opengl.util.FPSAnimator)


; ;;;;;;;;;;;;;;;;;;;;
; Constants
(def title "CSC 155 Project")
(def aspect (/ 16.0 9.0))
(def width 1000)
(def height (/ width aspect))
(def fps 60)
(def frame (JFrame.))
(def glCanvas (GLCanvas.)) 
(def fpsAnimator (FPSAnimator. glCanvas fps))


; ;;;;;;;;;;;;;;;;;;;;
; Initialize JFrame
(defn initialize-frame [frame]
  (-> frame (.getContentPane) (.add glCanvas))
  (.setTitle frame title)
  (.setSize frame width height)
  (.setDefaultCloseOperation frame JFrame/EXIT_ON_CLOSE)
  (.setLocationRelativeTo frame nil)
  (.setVisible frame true))



; ;;;;;;;;;;;;;;;;;;;;
; Initialize Scripts
(defn init-scripts []
  (util/add-script "src/main/res/ruby/main.rb"))



; ;;;;;;;;;;;;;;;;;;;;
; Main
(defn main [& args]
  (let [gameState
        {:camera (models/->Camera 0 0 0)
         :gameWorld [(models/->Triangle 0 0 0)]}
        glEventHandler (util/->GLEventHandler gameState)]
    (init-scripts)
    (initialize-frame frame)
    (.addGLEventListener glCanvas glEventHandler)
    (.setAnimator glCanvas fpsAnimator)
    (.start fpsAnimator)))



