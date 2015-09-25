package com.celestia.util

import java.nio.FloatBuffer
import javax.script.{CompiledScript, ScriptEngine, ScriptEngineManager}
import com.celestia.providers.{ServiceProvider, ScriptProvider}
import com.celestia.services.{UpdateService, RenderService}
import com.jogamp.opengl.{GL4, GLAutoDrawable, GLEventListener}
import com.celestia.factories.{GameObjectFactory, GLProgramBuilder}
import com.celestia.interfaces._
import com.celestia.models.{GameState, GLProgram}

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
  private lazy val renderService:IRenderService = serviceProvider.getRenderService
  private lazy val updateService:IUpdateService = serviceProvider.getUpdateService
  private lazy val collisionService:ICollisionService = serviceProvider.getCollisionService

  private var gameState:IGameState = GameObjectFactory.initGameState

  /**
   * Function for handling initialization of the GL components
   * of the game
   * @param glAutoDrawable
   */
  override def init(glAutoDrawable: GLAutoDrawable): Unit = {
    glProgram = glProgramBuilder.addShader(R.shaders.FragmentShader, R.shader_types.FRAGMENT_SHADER)
      .addShader(R.shaders.VertexShader, R.shader_types.VERTEX_SHADER)
      .build(glAutoDrawable)
    compiledScripts = initScripts
  }


  /**
   * Initialize scripts for scripting manager
   * @return
   */
  private def initScripts: List[CompiledScript] = scriptProvider.addScript(R.ruby.main).compileScripts


  /**
   * Function for rendering the game world
   * @param glAutoDrawable
   */
  override def display(glAutoDrawable: GLAutoDrawable): Unit = {
    gameState = updateService.update(gameState)
    collisionService.detectCollisions(gameState.gameWorld)
    compiledScripts.foreach((i)=>i.eval())
    renderService.render(gameState.gameWorld, glAutoDrawable)
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

  }

}
