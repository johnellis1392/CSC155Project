
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

;(import com.celestia.csc155.factories.GLProgramBuilder)


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
		;(.eval scriptEngine "puts 'Hello, World!'")))
		(doseq [script scripts]
			(.eval scriptEngine (FileReader. script)))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Functions for compiling shaders

(def shaders [])

; Add shader to collection
(defn add-shader [shader-path shader-type]
  (def shaders 
    (conj shaders 
      {:path shader-path 
       :type shader-type})))



; Compile all shaders
(defn compile-shaders [glAutoDrawable] 
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
  		  	    {:shaderSource shaderSource
  		  	     :shaderLineCounts shaderLineCounts
  		  	     :shaderLines shaderLines})) shaders)]
  		
  	; Iterate through shaders and link  	    
  	(doseq [shader shaderData]
      (let [shaderId (.glCreateShader gl (:type shader))]
        (.glShaderSource gl shaderId (:shaderLines shader) (:shaderSource shader) (:shaderLineCounts shader))
        (.glCompileShader shaderId)
        (GLSLUtils/printOpenGLError glAutoDrawable)
        (.glAttachShader gl programId shaderId)
        (.glDeleteShader shaderId)))
        
    ; Finally link program
    (.glLinkProgram programId)
    (GLSLUtils/printOpenGLError glAutoDrawable)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; GL Event Handler Object
(def VAO (int-array [0]))
(def VBO (int-array [0]))


(defrecord GLEventHandler [GameState]
  GLEventListener

  ; Initialize Game World
  (init [this glAutoDrawable]
    (let [gl (.getGL glAutoDrawable)]
      (add-shader "src/main/res/shaders/vshader.glsl" GL4/GL_VERTEX_SHADER)
      (add-shader "src/main/res/shaders/fshader.glsl" GL4/GL_FRAGMENT_SHADER)
      (compile-shaders glAutoDrawable)))
      
      ;(.glGenVertexArrays gl (.length VAO) VAO 0)
      ;(.glBindVertexArrays gl (get VAO 0))
      ;(.glGenBuffers gl (.length VBO) VBO 0)
		
      ;(.glBindBuffers gl GL4/GL_ARRAY_BUFFER (get VBO 0))
      ;()
      ;()))

  
  ; Update game world and Render
  (display [this glAutoDrawable]
    (let [gl (.getGL glAutoDrawable)]
      (.glClear gl GL4/GL_DEPTH_BUFFER_BIT)
      (try 
        (run-scripts glAutoDrawable)
        (catch Exception e
          (str "Exception Thrown: " (.getMessage e))))))
	
	
  (reshape [this glAutoDrawable x y w h]
    (let [gl (.getGL glAutoDrawable)]
      gl))
	
		
  (dispose [this glAutoDrawable]
    (let [gl (.getGL glAutoDrawable)]
      gl)))






















