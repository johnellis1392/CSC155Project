
(ns util
	(:gen-class))
	
(import com.jogamp.opengl.GLEventListener)
(import javax.script.ScriptEngineManager)
(import javax.script.ScriptEngine)
(import javax.script.ScriptContext)
(import javax.script.Bindings)

(import java.io.FileReader)

(import com.jogamp.opengl.GL4)
(import com.jogamp.opengl.GLAutoDrawable)



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

;(defn compile-shader [shader-path]
;	())




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; GL Event Handler Object
(def VAO (int-array [0]))
(def VBO (int-array [0]))


(defrecord GLEventHandler []
	GLEventListener
	
	(init [this glAutoDrawable]
		(let [gl (.getGL glAutoDrawable)]
			gl))
			;(.glGenVertexArrays gl (.length VAO) VAO 0)
			;(.glBindVertexArrays gl (get VAO 0))
			;(.glGenBuffers gl (.length VBO) VBO 0)
			
			;(.glBindBuffers gl GL4/GL_ARRAY_BUFFER (get VBO 0))
			;()
			;()))
	
		
	(display [this glAutoDrawable]
		(let [gl (.getGL glAutoDrawable)]
			(.glClear gl GL4/GL_DEPTH_BUFFER_BIT)
			(run-scripts glAutoDrawable)))
	
	
	(reshape [this glAutoDrawable x y w h]
		(let [gl (.getGL glAutoDrawable)]
			gl))
	
		
	(dispose [this glAutoDrawable]
		(let [gl (.getGL glAutoDrawable)]
			gl)))






















