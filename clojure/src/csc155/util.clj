
(ns csc155.util
  (:require [csc155.models :as models]))

	
(import com.jogamp.opengl.GLEventListener)
(import javax.script.ScriptEngineManager)
(import javax.script.ScriptEngine)
(import javax.script.ScriptContext)
(import javax.script.Bindings)
(import java.io.FileReader)
(import java.nio.IntBuffer)
(import java.nio.FloatBuffer)

(import java.awt.event.MouseWheelListener)
(import java.awt.event.MouseWheelEvent)
(import java.awt.event.MouseListener)
(import java.awt.event.MouseEvent)
(import java.awt.event.KeyListener)
(import java.awt.event.KeyEvent)


(import graphicslib3D.GLSLUtils)
(import graphicslib3D.Matrix3D)
(import graphicslib3D.Vector3D)
(import com.jogamp.opengl.GL4)
(import com.jogamp.opengl.GLAutoDrawable)


; Global Variables
(def VAO (int-array [0]))
(def VBO (int-array [0 0]))
(def gameState [])




(def vertex_position 
  [-0.25   0.25  -0.25  
   -0.25  -0.25  -0.25  
   0.25  -0.25  -0.25 
   0.25  -0.25  -0.25  
   0.25   0.25  -0.25  
   -0.25   0.25  -0.25 
   0.25  -0.25  -0.25  
   0.25  -0.25   0.25  
   0.25   0.25  -0.25 
   0.25  -0.25   0.25  
   0.25   0.25   0.25  
   0.25   0.25  -0.25
   0.25  -0.25   0.25  
   -0.25  -0.25   0.25  
   0.25   0.25   0.25 
   -0.25  -0.25   0.25  
   -0.25   0.25   0.25  
   0.25   0.25   0.25 
   -0.25  -0.25   0.25  
   -0.25  -0.25  -0.25  
   -0.25   0.25   0.25 
   -0.25  -0.25  -0.25  
   -0.25   0.25  -0.25  
   -0.25   0.25   0.25 
   -0.25  -0.25   0.25   
   0.25  -0.25   0.25   
   0.25  -0.25  -0.25 
   0.25  -0.25  -0.25  
   -0.25  -0.25  -0.25  
   -0.25  -0.25   0.25 
   -0.25   0.25  -0.25  
   0.25   0.25  -0.25  
   0.25   0.25   0.25 
   0.25   0.25   0.25  
   -0.25   0.25   0.25 
   -0.25   0.25  -0.25])


; Took this color data from a gl tutorial;
; http://www.opengl-tutorial.org/beginners-tutorials/tutorial-4-a-colored-cube/
(def vertex_colors 
   [0.583  0.771  0.014
    0.609  0.115  0.436
    0.327  0.483  0.844
    0.822  0.569  0.201
    0.435  0.602  0.223
    0.310  0.747  0.185
    0.597  0.770  0.761
    0.559  0.436  0.730
    0.359  0.583  0.152
    0.483  0.596  0.789
    0.559  0.861  0.639
    0.195  0.548  0.859
    0.014  0.184  0.576
    0.771  0.328  0.970
    0.406  0.615  0.116
    0.676  0.977  0.133
    0.971  0.572  0.833
    0.140  0.616  0.489
    0.997  0.513  0.064
    0.945  0.719  0.592
    0.543  0.021  0.978
    0.279  0.317  0.505
    0.167  0.620  0.077
    0.347  0.857  0.137
    0.055  0.953  0.042
    0.714  0.505  0.345
    0.783  0.290  0.734
    0.722  0.645  0.174
    0.302  0.455  0.848
    0.225  0.587  0.040
    0.517  0.713  0.338
    0.053  0.959  0.120
    0.393  0.621  0.362
    0.673  0.211  0.457
    0.820  0.883  0.371
    0.982  0.099  0.879])


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Functions for adding scripts
(def scripts [])
(def engine "jruby")
(def scriptEngineManager (ScriptEngineManager.))
(def scriptEngine (-> scriptEngineManager (.getEngineByName engine)))


; Add script to collection
(defn add-script [path]
  (def scripts (conj scripts path)))
	

; Run all scripts in collection
(defn run-scripts [glAutoDrawable gameState]
  (let [bindings (-> scriptEngine (.getBindings ScriptContext/ENGINE_SCOPE))
        gl (.getGL glAutoDrawable)] 
    (.put bindings "gl" gl)
    (.put bindings "gameState" gameState)
    (.put bindings "VAO" VAO)
    (.put bindings "VBO" VBO)
    (.put bindings "GL_DEPTH_BUFFER_BIT" GL4/GL_DEPTH_BUFFER_BIT)
    (.put bindings "GL_TRIANGLES" GL4/GL_TRIANGLES)
    (.put bindings "GL_COLOR" GL4/GL_COLOR)
    (.put bindings "GL_ARRAY_BUFFER" GL4/GL_ARRAY_BUFFER)
    
    (.put bindings "test_array" [1 2 3])

    (doseq [script scripts]
      (try 
        (.eval scriptEngine (FileReader. script))
        (catch Exception e
          (str "Exception Thrown: " (.getMessage e)))))))





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Matrix transform utilities

; Get Perspective Transform Matrix
(defn perspective [fov_y aspect n f]
  (let [q (->> fov_y (* 0.5) Math/toRadians Math/tan (/ 1.0))
        a (/ q aspect)
        b (/ (+ n f) (- n f))
        c (/ (-> f (* n) (* 2.0)) (- n f))
        r (Matrix3D.)]
    (.setElementAt r 0 0 a)
    (.setElementAt r 1 1 q)
    (.setElementAt r 2 2 b)
    (.setElementAt r 2 3 -1.0)
    (.setElementAt r 3 2 c)
    (let [rt (.transpose r)]
      rt)))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Functions for compiling shaders

(def programId 0)
(def shaders [])

; Add shader to collection
(defn add-shader [shader-path shader-type]
  (def shaders 
    (conj shaders 
      {:path shader-path 
       :type shader-type})))


; Print GL Shader Compilation Error
(defn print-shader-error [glAutoDrawable shaderId]
  "Print any GL Shader Compilation Errors that may have occurred"
  (let [gl (.getGL glAutoDrawable)
        compile-status (int-array [0])]
    (GLSLUtils/printOpenGLError glAutoDrawable)
    (.glGetShaderiv gl shaderId GL4/GL_COMPILE_STATUS compile-status 0)
    (if (= 1 (first compile-status))
      (println "Shader Compiled Successfully.")
      (do
        (println "Shader Compilation Failed.")
        (GLSLUtils/printShaderInfoLog glAutoDrawable shaderId)))))



; Print GL Program Compilation Error
(defn print-program-error [glAutoDrawable programId]
  "Print any GL Program Linking Errors that may have occurred"
  (let [gl (.getGL glAutoDrawable)
        link-status (int-array [0])]
    (GLSLUtils/printOpenGLError glAutoDrawable)
    (.glGetProgramiv gl programId GL4/GL_LINK_STATUS link-status 0)
    (if (= 1 (first link-status))
      (println "Linking Successful.")
      (do
        (println "Linking Failed.")
        (GLSLUtils/printProgramInfoLog glAutoDrawable programId)))))



; Compile all shaders in list
(defn compile-shaders [glAutoDrawable]
  "Compile all shaders" 
  (let [gl (.getGL glAutoDrawable)
        compileStatus (int-array 1)
        linkStatus (int-array 1)
        programId (.glCreateProgram gl)
        
        shaderData 
        (map 
         (fn [shader] 
           (let [shaderSource (GLSLUtils/readShaderSource (:path shader))
                 shaderLineCounts (map #(.length %) shaderSource)
                 shaderLines (count shaderSource)]
             {:source shaderSource
              :line-counts shaderLineCounts
              :lines shaderLines
              :type (:type shader)}))
         shaders)]
    
    (doseq [shader shaderData]
      "Iterate through shaders and link"
      (let [shaderId (.glCreateShader gl (:type shader))]
        (.glShaderSource gl shaderId
                         (:lines shader)
                         (:source shader)
                         (IntBuffer/wrap
                          (int-array
                           (:line-counts shader))))
        (.glCompileShader gl shaderId)
        (print-shader-error glAutoDrawable shaderId)
        (.glAttachShader gl programId shaderId)
        (.glDeleteShader gl shaderId)))
    (.glLinkProgram gl programId)
    (print-program-error glAutoDrawable programId)
    programId))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Functions for dealing with event queue
(def event-queue [])

; Add a function to the event queue
(defn enqueue [f]
  (def event-queue (conj event-queue f)))

; Iterate through all actions in queue and mutate game
; state 
(defn eval-events [mGameState]
  (reduce (fn [g f] (f g)) mGameState event-queue))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; GL Event Handler Object


; Color Vectors for cubes
(def cubeColor
  [(float-array [0.0 0.8 1.0 1.0])
   (float-array [0.5 0.5 0.5 0.0])]) 
  ;[(Vector3D. 0.0 0.8 1.0 1.0) 
   ;(Vector3D. 0.5 0.5 0.5 0.0)])
   
           
  
(defrecord GLEventHandler 
  [_gameState]
  GLEventListener
  
  (init
   [this glAutoDrawable]
   (let [vertex_buffer (FloatBuffer/wrap (float-array vertex_position))
		 gl (-> glAutoDrawable .getGL)]
     (def gameState _gameState)
     
     (add-shader "src/main/res/shaders/vshader.glsl" GL4/GL_VERTEX_SHADER)
     (add-shader "src/main/res/shaders/fshader.glsl" GL4/GL_FRAGMENT_SHADER)
     (def programId (compile-shaders glAutoDrawable))
     
     ; Initialize Vertex Arrays & Buffers
     (.glGenVertexArrays gl (count VAO) VAO 0)
     (.glBindVertexArray gl (first VAO))
     (.glGenBuffers gl (count VBO) VBO 0)
     
     ;(.glBindBuffer gl GL4/GL_ARRAY_BUFFER (second VBO))
     ;(.glBufferData gl GL4/GL_ARRAY_BUFFER (count vertex_colors))
     ;(.glVertexAttribPointer gl 1 3 GL4/GL_FLOAT false 0 0)
     
     ;(.glBindBuffer gl GL4/GL_ARRAY_BUFFER (first VBO))
     ;(.glBufferData gl GL4/GL_ARRAY_BUFFER (-> vertex_buffer .limit (* 4)) vertex_buffer GL4/GL_STATIC_DRAW)
     
     
     (-> gameState :camera (init glAutoDrawable))
     ;(map #(init % glAutoDrawable) (-> gameState :gameWorld))
     ))
  
  
  
  (display
   [this glAutoDrawable]
   (let [gl (.getGL glAutoDrawable)
         aspect (/ 16.0 9.0)
         mv_location    (.glGetUniformLocation gl programId "model_view_matrix")
         proj_location  (.glGetUniformLocation gl programId "projection_matrix")
         
         fovy (-> gameState :camera :zoom)
         perspective_matrix (perspective fovy aspect 0.1 1000.0)
         view_matrix (Matrix3D.)
         model_matrix (Matrix3D.)
         mv_matrix (Matrix3D.)
         
         background (FloatBuffer/allocate 4)]
 
     ; Update GameState Object
     (def gameState (eval-events gameState))
     
     ; Set Matrices
     (.translate view_matrix 
       (-> gameState :camera :x)
       (-> gameState :camera :y)
       (-> gameState :camera :z))
	 (.translate model_matrix 0.0 -0.5 0.0)
     (.concatenate mv_matrix view_matrix)
     (.concatenate mv_matrix model_matrix)
       
     ; Set background
     (.put background 0 0.0)
     (.put background 1 0.0)
     (.put background 2 0.0)
     (.put background 3 0.0)
     (.glClear gl GL4/GL_DEPTH_BUFFER_BIT)
     (.glClearBufferfv gl GL4/GL_COLOR 0 background)
     
     ; Set current program
     (.glUseProgram gl programId)
     
     ; Run Rendering Scripts
     ;(run-scripts glAutoDrawable gameState)
     
     ; Set Uniform Constants for GL
     (.glUniformMatrix4fv gl mv_location 1 false (.getFloatValues mv_matrix) 0)
     (.glUniformMatrix4fv gl proj_location 1 false (.getFloatValues perspective_matrix) 0)
     
     ; Bind the vertices to render
     (.glBindBuffer gl GL4/GL_ARRAY_BUFFER (first VBO))
     (.glVertexAttribPointer gl 0 3 GL4/GL_FLOAT false 0 0)
     (.glEnableVertexAttribArray gl 0)
     
     ; Enable GL Rendering Attributes
     (.glEnable gl GL4/GL_CULL_FACE)
     (.glEnable gl GL4/GL_CW)
     (.glEnable gl GL4/GL_DEPTH_TEST)
     (.glEnable gl GL4/GL_LEQUAL)
     
     ; Draw objects
     (.glDrawArrays gl GL4/GL_TRIANGLES 0 (count vertex_position))
     ))
     
     
  (reshape
    [this glAutoDrawable x y w h]
    (let [gl (.getGL glAutoDrawable)]
      gl))



  (dispose
    [this glAutoDrawable]
    (let [gl (.getGL glAutoDrawable)]
 	  ;(.glDeleteVertexArrays gl 1 VAO)
      ;(.glDeleteProgram gl programId)
      gl)))
  











;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Objects for handling user input

; Mouse Event Handler
(defrecord MouseHandler []
  MouseListener
  
  (mouseExited [this mouseEvent] 
    ())
    
  (mouseClicked [this mouseEvent] 
    ())
    
  ; Change Cube Color
  (mousePressed [this mouseEvent] 
    (def cubeColor [(second cubeColor) (first cubeColor)]))
    
  (mouseReleased [this mouseEvent] 
    ())
    
  (mouseEntered [this mouseEvent] 
    ()))

  
  
; Utilities for checking key codes
(defn up?    [keyCode] (or (= keyCode KeyEvent/VK_UP) (= keyCode KeyEvent/VK_W)))
(defn down?  [keyCode] (or (= keyCode KeyEvent/VK_DOWN) (= keyCode KeyEvent/VK_S)))
(defn left?  [keyCode] (or (= keyCode KeyEvent/VK_LEFT) (= keyCode KeyEvent/VK_A)))
(defn right? [keyCode] (or (= keyCode KeyEvent/VK_RIGHT) (= keyCode KeyEvent/VK_D)))


; Key Event Handler
(defrecord KeyHandler []
  KeyListener
  
  (keyPressed [_ keyEvent] 
    "Handle Key Press"
    (let [keyCode (.getKeyCode keyEvent)
          speed 0.02
          dx (+ (-> gameState :camera :x) 
                (cond (left?  keyCode) speed
                      (right? keyCode) (* -1 speed)
                      :else 0))
          dy (+ (-> gameState :camera :y)
                (cond (up?   keyCode) (* -1 speed)
                      (down? keyCode) speed
                      :else 0))
          dz (+ (-> gameState :camera :z)
                0)
          zoom (-> gameState :camera :zoom)
          camera (->Camera dx dy dz zoom)]
      (enqueue #(assoc % :camera camera))))
  
  (keyReleased [_ keyEvent] 
    (let [keyCode (.getKeyCode keyEvent)]
      nil))
  
  (keyTyped [_ keyEvent] 
    (let [keyCode (.getKeyCode keyEvent)]
      nil)))



(defrecord MouseWheelHandler []
  MouseWheelListener
  (mouseWheelMoved [_ mouseWheelEvent]
    (let [speed 10.0
          mouseWheelMovement (-> mouseWheelEvent .getWheelRotation)
          x (-> gameState :camera :x)
          y (-> gameState :camera :y)
          z (-> gameState :camera :z)
          zoom (+ (* speed mouseWheelMovement) (-> gameState :camera :zoom))
          camera (models/->Camera x y z zoom)]
      (enqueue #(assoc % :camera camera)))))
      
  

























