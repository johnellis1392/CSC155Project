
;; Event Handler class 
(gen-class 
    :name com.celestia.util.GLEventHandler
    :implements com.jogamp.opengl.GLEventListener
    
    (defn -init [] ())
    
    (defn -display [glAutoDrawable] 
        (let [[gl (.getGL glAutoDrawable)]]
            (loop for gameObject in mGameState
                  (do 
                      (.update gameObject)))
                  
            (loop for gameObject in mGameState
                  (do 
                      (.detectCollisions gameObject)))
                  
            (loop for gameObject in mGameState 
                  (do 
                      (.render gameObject)))))
    
    (defn -dispose [glAutoDrawable] 
        (let [[gl (.getGL glAutoDrawable)]]
             ()))
    
    (defn -reshape [glAutoDrawable] 
        (let [[gl (.getGL glAutoDrawable]] 
              ()))))

