
(ns util
  (:gen-class))
	
(import com.jogamp.opengl.GLEventListener)
(import javax.script.ScriptEngineManager)
(import javax.script.ScriptEngine)
(import javax.script.ScriptContext)
(import javax.script.Bindings)

(import java.io.FileReader)
(import graphicslib3D.GLSLUtils)
(import com.jogamp.opengl.GL4)
(import com.jogamp.opengl.GLAutoDrawable)
(import java.nio.IntBuffer)

(require '[models :refer :all])
(require '[main :refer :all])


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
(defn run-scripts [glAutoDrawable]
  (let [bindings (-> scriptEngine (.getBindings ScriptContext/ENGINE_SCOPE))
        gl (.getGL glAutoDrawable)] 
    (.put bindings "gl" gl)
    (.put bindings "GL_DEPTH_BUFFER_BIT" GL4/GL_DEPTH_BUFFER_BIT)
    (.put bindings "GL_TRIANGLES" GL4/GL_TRIANGLES)
    (.put bindings "GL_COLOR" GL4/GL_COLOR)

    (doseq [script scripts]
      (try 
        (.eval scriptEngine (FileReader. script))
        (catch Exception e
          (str "Exception Thrown: " (.getMessage e)))))))




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
; GL Event Handler Object
(def VAO (int-array [0]))
(def VBO (int-array [0]))

;(def gameState {})


(defrecord GLEventHandler [gs]
  GLEventListener
  
  ; Initialize Game World
  (init
   [this glAutoDrawable]
   (let [gl (.getGL glAutoDrawable)]
     ;(def gameState _gameState)
     (init (-> gameState :camera) glAutoDrawable)
     (map #(init % glAutoDrawable) (-> gameState :gameWorld))
     
     (add-shader "src/main/res/shaders/vshader.glsl" GL4/GL_VERTEX_SHADER)
     (add-shader "src/main/res/shaders/fshader.glsl" GL4/GL_FRAGMENT_SHADER)
     (def programId (compile-shaders glAutoDrawable))))
  

  ; Update game world and Render
  (display
   [this glAutoDrawable]
   (let [gl (.getGL glAutoDrawable)]
     (.glUseProgram gl programId)
     (.glClear gl GL4/GL_DEPTH_BUFFER_BIT)
     (def gameState
       {:camera (update (-> gameState :camera) 0)
        :gameWorld (map #(update % 0) (-> gameState :gameWorld))})
     (run-scripts glAutoDrawable)))
  
  
  ; Reshape the view screen
  (reshape
   [this glAutoDrawable x y w h]
   (let [gl (.getGL glAutoDrawable)]
     gl))
  

  ; Dispose all graphics objects
  (dispose
   [this glAutoDrawable]
   (let [gl (.getGL glAutoDrawable)]
     gl)))


