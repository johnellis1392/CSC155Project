
(import javax.swing.JFrame) 
(import com.jogamp.opengl.GLCanvas) 
(import com.celestia.util.GLEventHandler) 
(import com.celestia.services.RenderService) 
(import com.celestia.services.UpdateService) 

(def fps 60)
(def frame (JFrame.)) 
(def glCanvas (GLCanvas.)) 
(def fpsAnimator (FPSAnimator. fps)) 
(def glEventHandler (GLEventHandler.)) 

(defn -main [& args] 
    (init)
    (.start fpsAnimator))

(defn init []
    (.setDefaultCloseOperation frame JFrame/EXIT_ON_CLOSE)
    (.addComponent (.getContentPane frame) glCanvas)
    (.setEventListener glCanvas glEventHandler)
    (.setAnimator glCanvas fpsAnimator))

