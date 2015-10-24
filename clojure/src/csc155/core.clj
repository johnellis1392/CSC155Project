(ns csc155.core
  (:require [csc155.util :as util]
            [csc155.models :as models]))

;(defn -main
;  []
;  (println "Hello, World!"))


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
(defn -main [& args]
  (let [gameState
        {:camera (models/->Camera 0 0 -1 50.0)
         :gameWorld [(models/->Triangle 0 0 0)]}
        glEventHandler (util/->GLEventHandler gameState)
        mouseHandler (util/->MouseHandler)
        keyHandler (util/->KeyHandler)
        mouseWheelHandler (util/->MouseWheelHandler)]
    (init-scripts)
    (initialize-frame frame)
    (.addGLEventListener glCanvas glEventHandler)
    (.addMouseWheelListener glCanvas mouseWheelHandler)
    (.addMouseListener glCanvas mouseHandler)
    (.addKeyListener glCanvas keyHandler)
    (.setAnimator glCanvas fpsAnimator)
    (.start fpsAnimator)))



