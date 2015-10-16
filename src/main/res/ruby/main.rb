
import java.nio.FloatBuffer 
import java.lang.System
#import graphicslib3D.Matrix3D

require 'java'
$CLASSPATH << 'lib/graphicslib3D'
#require 'graphicslib3D'


# Triangle class for storing position data
class Triangle
	def initialize(position, rotation, scale)
		@position = position
		@rotation = rotation
		@scale = scale
	end
	
	def initialize(data)
		@position = data.position
		@rotation = data.rotation
		@scale = data.scale
	end
end


gl = $gl
gl.glClear($GL_DEPTH_BUFFER_BIT)

# Set background color
background = FloatBuffer.allocate(4)
background.put(0, Math.sin(System.currentTimeMillis()) * 0.05 + 1)
background.put(1, Math.cos(System.currentTimeMillis()) * 0.05)
#background.put(0, 0.9)
#background.put(1, 0.9)
background.put(2, 0.9)
background.put(3, 0.7)
gl.glClearBufferfv($GL_COLOR, 0, background) 

#m = new Matrix3D

#gl.glDrawArrays($GL_TRIANGLES, 0, 1)






#puts 'Hello, World!'


