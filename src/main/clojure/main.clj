
(ns main-ns
  (:gen-class))

(require '[util :refer :all])
(require '[models :refer :all])

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
(def glCanvas (GLCanvas.))
(def fpsAnimator (FPSAnimator. glCanvas fps))

(def gameState
  {:camera (->Camera 0 0 0)
   :gameWorld [(->Triangle 0 0 0)]})
(def glEventHandler (->GLEventHandler gameState))


; ;;;;;;;;;;;;;;;;;;;;
; Initialize JFrame
(defn initialize-frame [frame]
  (-> frame (.getContentPane) (.add glCanvas))
  (.setTitle frame title)
  (.setSize frame width height)
  (.setDefaultCloseOperation frame JFrame/EXIT_ON_CLOSE)
  (.setLocationRelativeTo frame nil)
  (.addGLEventListener glCanvas glEventHandler)
  (.setAnimator glCanvas fpsAnimator)
  (.setVisible frame true)
  (.start fpsAnimator))



; ;;;;;;;;;;;;;;;;;;;;
; Initialize Scripts
(defn init-scripts []
  (add-script "src/main/res/ruby/main.rb"))



; ;;;;;;;;;;;;;;;;;;;;
; Main
(defn main [& args]
  (init-scripts)
  (initialize-frame frame))



