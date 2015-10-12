
import java.nio.FloatBuffer 
import java.lang.System

require 'java'
$CLASSPATH << 'lib/graphicslib3D'
#require 'graphicslib3D'
#import graphicslib3D.Matrix3D

=begin

-- API --

onLoad()
onInit()
onUpdate(gameWorld)
onRender(gl)
onDispose()

=end


#puts "Hello, World! :D" 


=begin
# 
# Function callback for initializing game world
# 
def onInit(gameState)
  puts "Init"
end


# 
# Function callback for updating game state 
# 
def onUpdate(gameState)
  puts "Update"
end


# 
# Function callback for rendering gl images
# 
def onDisplay(glAutoDrawable)
  #gl = glAutoDrawable.getGL()
  puts "Display" 
end

  
def onDispose(glAutoDrawable)
  puts "Dispose"
end
=end

#puts 'Hello, World! 2' 


=begin
# Wrapper class for scala hash maps
class ScalaHashMap
  def initialize(hash)
    @hash = hash
  end

  def each
    @hash.size().times{|key| yield key, @hash.get(key).get}
  end
end


# Wrapper class for JOGL GL Context
class PanelGL < JFrame
  def initialize(parent = nil)
    super
    @camera = Register.cameras.get(0).get
    profile = GLProfile.get(GLProfile::GL3)
    glCaps = GLCapabilities.new(profile)
    glCaps.setPBuffer true
    @pBuffer = GLDrawableFactory.getFactory(profile).createGLPbuffer(GLCapabilitiesChooser.new(), 1, 1, nil)
  end

  attr_reader :camera

  def initializeGL
    @ctx = @pBuffer.getContext()
  end

  def resizeGL(width, height)
    @ctx.makeCurrent()
    gl = @pBuffer.getContext().getGL.getGL3
    gl.glViewport(0, 0, width, height)
  end

  def paintGL
    @gl = @pBuffer.getContext().getGL.getGL3
    @gl.glClearColor(0.3, 0.3, 0.3, 0.0)
    @gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT)
    @gl.glEnable(GL.GL_DEPTH_TEST)
    @gl.glDisable(GL.GL_DEPTH_TEST) 
  end
end
=end


gl = $gl
gl.glClear($GL_DEPTH_BUFFER_BIT)

# Set background color
background = FloatBuffer.allocate(4)
#background.put(0, Math.sin(System.currentTimeMillis()) * 0.5 + 0.5)
#background.put(1, Math.cos(System.currentTimeMillis()) * 0.5 + 0.5)
background.put(0, 0.0)
background.put(1, 1.0)
background.put(2, 1.0)
background.put(3, 1.0)
gl.glClearBufferfv($GL_COLOR, 0, background) 

#m = new Matrix3D

gl.glDrawArrays($GL_TRIANGLES, 0, 1)















