
(ns util
  ;(:gen-class)
  (:require [models :refer :all]))
	
(import com.jogamp.opengl.GLEventListener)
(import javax.script.ScriptEngineManager)
(import javax.script.ScriptEngine)
(import javax.script.ScriptContext)
(import javax.script.Bindings)
(import java.io.FileReader)
(import java.nio.IntBuffer)
(import java.nio.FloatBuffer)

(import java.awt.event.MouseListener)
(import java.awt.event.MouseEvent)
(import java.awt.event.KeyListener)
(import java.awt.event.KeyEvent)


(import graphicslib3D.GLSLUtils)
(import graphicslib3D.Matrix3D)
(import com.jogamp.opengl.GL4)
(import com.jogamp.opengl.GLAutoDrawable)


; Global Variables
(def VAO (int-array [0]))
(def VBO (int-array [0]))
(def gameState [])




(def vertex_position [-0.25   0.25  -0.25  -0.25  -0.25  -0.25  0.25  -0.25  -0.25 
             	0.25  -0.25  -0.25  0.25   0.25  -0.25  -0.25   0.25  -0.25 
             	0.25  -0.25  -0.25  0.25  -0.25   0.25  0.25   0.25  -0.25 
             	0.25  -0.25   0.25  0.25   0.25   0.25  0.25   0.25  -0.25 
             	0.25  -0.25   0.25  -0.25  -0.25   0.25  0.25   0.25   0.25 
            	-0.25  -0.25   0.25  -0.25   0.25   0.25  0.25   0.25   0.25 
            	-0.25  -0.25   0.25  -0.25  -0.25  -0.25  -0.25   0.25   0.25 
            	-0.25  -0.25  -0.25  -0.25   0.25  -0.25  -0.25   0.25   0.25 
            	-0.25  -0.25   0.25   0.25  -0.25   0.25   0.25  -0.25  -0.25 
             	0.25  -0.25  -0.25  -0.25  -0.25  -0.25  -0.25  -0.25   0.25 
		        -0.25   0.25  -0.25  0.25   0.25  -0.25  0.25   0.25   0.25 
             	0.25   0.25   0.25  -0.25   0.25   0.25  -0.25   0.25  -0.25])



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
  (reduce #(%2 %1) mGameState event-queue))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; GL Event Handler Object



  
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
     
     (.glBindBuffer gl GL4/GL_ARRAY_BUFFER (first VBO))
     (.glBufferData gl GL4/GL_ARRAY_BUFFER (-> vertex_buffer .limit (* 4)) vertex_buffer GL4/GL_STATIC_DRAW)
     
     
     (-> gameState :camera (init glAutoDrawable))
     ;(map #(init % glAutoDrawable) (-> gameState :gameWorld))
     ))
  
  
  
  (display
   [this glAutoDrawable]
   (let [gl (.getGL glAutoDrawable)
         aspect (/ 16.0 9.0)
         mv_location (.glGetUniformLocation gl programId "model_view_matrix")
         proj_location (.glGetUniformLocation gl programId "projection_matrix")
         
         perspective_matrix (perspective 50.0 aspect 0.1 1000.0)
         view_matrix (Matrix3D.)
         model_matrix (Matrix3D.)
         mv_matrix (Matrix3D.)
         background (FloatBuffer/allocate 4)]
 
     ; Set Matrices
     (.translate view_matrix 0.0 0.0 -1.0)
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
  (mouseExited [this mouseEvent] ())
  (mouseClicked [this mouseEvent] ())
  (mousePressed [this mouseEvent] ())
  (mouseReleased [this mouseEvent] ())
  (mouseEntered [this mouseEvent] ()))

  

; Key Event Handler
(defrecord KeyHandler []
  KeyListener
  (keyPressed [this keyEvent] ())
  (keyReleased [this keyEvent] ())
  (keyTyped [this keyEvent] ()))

  
























