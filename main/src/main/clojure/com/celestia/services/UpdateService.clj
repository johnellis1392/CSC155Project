
;; Multimethod for handling Game World update
(defmulti update class [:IGameObject gameObject])

(defmethod update ::collection [gameWorld] 
    ())

(defmethod update :Triangle [gameObject]
    (:IGameObject :Triangle
                  :position (.translate (.position gameObject) 1)
                  :rotation (.rotate (.rotation gameObject) 1)
                  :scale (.scale (.scale gameObject) 1)))

(defmethod update :Sun [gameObject] 
    (:IGameObject :Sun (...))) 

(defmethod update :Moon [gameObject] 
    ()) 

