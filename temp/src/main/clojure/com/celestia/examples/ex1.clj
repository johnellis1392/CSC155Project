
(import javax.swing.JFrame)

(defn -main [& args]
	(let [frame (make-frame)
		  glCanvas (GLCanvas.)
		  color (FloatBuffer.)]
		(-> frame
			(.getContentPane)
			(.add glCanvas))
		(-> color
			(.put 0, 1.0f)
			(.put 1, 1.0f)
			(.put 2, 1.0f)
			(.put 3, 1.0f))
		(.setVisible frame true)))


(defn make-frame []
	(let [frame (JFrame.)]
		(-> frame
			(.setTitle "Example 1")
			(.setLocationRelativeTo nil))))
			
			
