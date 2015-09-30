
;; Render multimethod for rendering game objects
(defmulti render class [glAutoDrawable gameObject])

(defmethod render :Triangle [glAutoDrawable gameObject]
    (let [[gl (.getGL glAutoDrawable)]]
         (.glUniformAttribArray4fv gl (.position gameObject))
         (.glDrawArrays gl (.GL_TRIANGLES GL4))))

(defmethod render :Sun [glAutoDrawable gameObject]
    ())


