
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


#
# Simple Camera Wrapper
#
class Camera
  def initialize(c)
    @position = 0
    @rotation = 0
    @zoom = 0
  end
  
  def position
    @position
  end
  
  def rotation
    @rotation
  end
  
  def zoom
    @zoom
  end
end


#
# Simple Game Object Wrapper
#
def GameObject
  def initialize(go)
    @position = 0 
    @rotation = 0
    @scale = 0
  end
  
  def position
    @position
  end
  
  def rotation
    @rotation
  end
  
  def scale
    @scale
  end
end


#
# Game State Object
#
class GameState
  def initialize(gs)
    temp = gs.map{|k,v| {k.to_s => v}}
  	@camera = Camera.new temp[0][":camera"]
  	@gameWorld = temp[1][":gameWorld"].map{|i| GameObject.new i}
  end
  
  def camera
    @camera
  end
  
  def gameWorld
    @gameWorld
  end
end 



$gl.glClear($GL_DEPTH_BUFFER_BIT)

gameState = GameState.new $gameState
gameState.gameWorld.each {|i|
  puts i.to_s
}

#puts $gameState.map{|k,v| {k.to_s => v}}[1][":gameWorld"].inspect




#gameState = $gameState.map{|k,v| {k.to_s => v}}
#puts gameState[0][":camera"]
#puts gameState[0][":gameWorld"]

#puts '#############'
#gameState.each{|i|
#  #puts i
# puts i[":camera"]
#  puts i[":gameWorld"]
#}
#puts '#############'
#puts ''


# Set background color
background = FloatBuffer.allocate(4)
#background.put(0, Math.sin(System.currentTimeMillis()) * 0.05 + 1)
#background.put(1, Math.cos(System.currentTimeMillis()) * 0.05)

background.put(0, 0.0)
background.put(1, 0.0)
background.put(2, 0.0)
background.put(3, 0.0)

# Bind GL Buffer
$gl.glClearBufferfv($GL_COLOR, 0, background)
$gl.glBindBuffer($GL_ARRAY_BUFFER, $VBO[0])
$gl.glDrawArrays($GL_POINTS, 0, 1)






























