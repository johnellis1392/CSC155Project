package com.celestia.util

import java.nio.FloatBuffer
import javax.script.{CompiledScript, ScriptEngine, ScriptEngineManager}
import com.celestia.providers.{ServiceProvider, ScriptProvider}
import com.celestia.services.{UpdateService, RenderService}
import com.jogamp.opengl.{GL4, GLAutoDrawable, GLEventListener}
import com.celestia.factories.{GameObjectFactory, GLProgramBuilder}
import com.celestia.interfaces._
import com.celestia.models.{GameState, GLProgram}
import com.celestia.MainFrame
import graphicslib3D.Matrix3D

/**
 * Created by celestia on 9/24/15.
 */
class GLEventHandler extends GLEventListener {

  // Values for holding the state of the game
  private lazy val glProgramBuilder:IGLProgramBuilder = new GLProgramBuilder
  private var glProgram:GLProgram = new GLProgram(-1, List[Int]())

  private lazy val scriptProvider:IScriptProvider = new ScriptProvider()
  private var compiledScripts:List[CompiledScript] = List()

  private lazy val serviceProvider:IServiceProvider = new ServiceProvider
  private lazy val updateService:IUpdateService = serviceProvider.getUpdateService
  private lazy val collisionService:ICollisionService = serviceProvider.getCollisionService
  private lazy val renderService:IRenderService = serviceProvider.getRenderService

  private var gameState:IGameState = GameObjectFactory.initGameState
  private val VAO:Array[Int] = new Array[Int](1)
  private val VBO:Array[Int] = new Array[Int](1)

  /**
   * Function for handling initialization of the GL components
   * of the game
   * @param glAutoDrawable
   */
  override def init(glAutoDrawable: GLAutoDrawable): Unit = {
    val gl:GL4 = glAutoDrawable.getGL.asInstanceOf[GL4]
    glProgram = glProgramBuilder.addShader(R.shaders.FragmentShader, R.gl.shaders.FRAGMENT_SHADER)
      .addShader(R.shaders.VertexShader, R.gl.shaders.VERTEX_SHADER)
      .build(glAutoDrawable)
    compiledScripts = initScripts

//    gl.glGenVertexArrays(VAO.length, VAO, 0)
//    gl.glBindVertexArray(VAO(0))

    gl.glGenVertexArrays(VAO.length, VAO, 0)
    gl.glBindVertexArray(VAO(0))
    gl.glGenBuffers(VBO.length, VBO, 0)

    gl.glBindBuffer(R.gl.GL_ARRAY_BUFFER, VBO(0))
    val pyramidBuffer:FloatBuffer = FloatBuffer.wrap(R.vertices.pyramid)
    gl.glBufferData(R.gl.GL_ARRAY_BUFFER, pyramidBuffer.limit() * 4, pyramidBuffer, R.gl.GL_STATIC_DRAW)

  }


  /**
   * Initialize scripts for scripting manager
   * @return
   */
  private def initScripts: List[CompiledScript] =
    scriptProvider.addScript(R.ruby.main)
      .compileScripts



  // Temporary values for handling display
  var x:Float = 0.0f
  var y:Float = 0.0f
  var z:Float = -0.5f

  /**
   * Function for rendering the game world
   * @param glAutoDrawable
   */
  override def display(gLAutoDrawable: GLAutoDrawable):Unit={

  }

//  override def display(glAutoDrawable: GLAutoDrawable): Unit = {
//    val gl:GL4 = glAutoDrawable.getGL.asInstanceOf[GL4]
//    gameState = updateService.update(gameState)
//    collisionService.detectCollisions(gameState.gameWorld)
//    compiledScripts.foreach((i)=>i.eval())
//
//    gl.glClear(R.gl.GL_DEPTH_BUFFER_BIT)
//    gl.glUseProgram(glProgram.programId)
//
////    val mvLocation:Int = gl.glGetUniformLocation(glProgram.programId, R.gl.uniforms.model_view_matrix)
////    val projectionLocation:Int = gl.glGetUniformLocation(glProgram.programId, R.gl.uniforms.projection_matrix)
//
//    val viewMatrix:Matrix3D = new Matrix3D()
//    viewMatrix.translate(
//      -gameState.camera.x,
//      -gameState.camera.y,
//      -gameState.camera.z
//    )
//
//    val modelMatrix:Matrix3D = new Matrix3D()
////    val amount:Double = System.currentTimeMillis() % 3600 / 10.0
//
////    modelMatrix.rotate(amount, amount, amount)
//    modelMatrix.translate(x, y, z)
//    modelMatrix.rotateX(-20.0f)
//    modelMatrix.rotateY(35.0f)
//
//    val modelViewMatrix:Matrix3D = new Matrix3D()
//    modelViewMatrix.concatenate(viewMatrix)
//    modelViewMatrix.concatenate(modelMatrix)
//
////    val aspect:Float = R.util.aspect.toFloat
////    val projectionMatrix:Matrix3D = perspective(50.0f, aspect, 0.1f, 1000.0f)
//
////    gl.glUniformMatrix4fv(mvLocation, 1, false, modelViewMatrix.getFloatValues, 0)
////    gl.glUniformMatrix4fv(projectionLocation, 1, false, projectionMatrix.getFloatValues, 0)
//
//    gl.glBindBuffer(R.gl.GL_ARRAY_BUFFER, VBO(0))
//    gl.glVertexAttribPointer(0, 3, R.gl.GL_FLOAT, false, 0, 0)
//    gl.glEnableVertexAttribArray(1)
//
//    gl.glEnable(R.gl.GL_CULL_FACE)
//    gl.glFrontFace(R.gl.GL_CCW)
//    gl.glEnable(R.gl.GL_DEPTH_TEST)
//    gl.glDepthFunc(R.gl.GL_LEQUAL)
//
//    gl.glDrawArrays(R.gl.GL_TRIANGLES, 0, 18)
////    renderService.render(gameState, glAutoDrawable)
//  }


  private def perspective(fovy:Float, aspect:Float, n:Float, f:Float):Matrix3D={
    val q:Float = (1.0f / Math.tan(Math.toRadians(0.5f * fovy))).toFloat
    val A:Float = q / aspect
    val B:Float = (n + f) / (n - f)
    val C:Float = (2.0f * n * f) / (n - f)
    val r:Matrix3D = new Matrix3D()

    r.setElementAt(0,0,A)
    r.setElementAt(1,1,q)
    r.setElementAt(2,2,B)
    r.setElementAt(2,3,-1.0f)
    r.setElementAt(3,2,C)
    r.transpose()
  }


  /**
   * Function for reshaping the frame
   * @param glAutoDrawable
   * @param i
   * @param i1
   * @param i2
   * @param i3
   */
  override def reshape(glAutoDrawable: GLAutoDrawable, i: Int, i1: Int, i2: Int, i3: Int): Unit = {

  }


  /**
   * Function for disposing of the gl components
   * @param glAutoDrawable
   */
  override def dispose(glAutoDrawable: GLAutoDrawable): Unit = {
    val gl:GL4 = glAutoDrawable.getGL.asInstanceOf[GL4]
    gl.glDeleteProgram(glProgram.programId)
  }

}
