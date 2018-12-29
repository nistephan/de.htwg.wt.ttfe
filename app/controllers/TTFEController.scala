package controllers

import javax.inject._

import play.api.mvc._
import de.htwg.se.ttfe.TTFE

@Singleton
class TTFEController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  val gameController = TTFE.controller
  def ttfeAsText =  gameController.fieldToString

  def ttfe = Action {
    Ok(views.html.ttfe(gameController, "Test"))
  }

  def left = Action {
    gameController.moveDirection("L")
    Ok(views.html.ttfe(gameController, "Test"))
  }

  def right = Action {
    gameController.moveDirection("R")
    Ok(views.html.ttfe(gameController, "Test"))
  }

  def up = Action {
    gameController.moveDirection("U")
    Ok(views.html.ttfe(gameController, "Test"))
  }

  def down = Action {
    gameController.moveDirection("D")
    Ok(views.html.ttfe(gameController, "Test"))
  }

  def restart = Action {
    gameController.restart()
    Ok(views.html.ttfe(gameController, "Test"))
  }

  def about = Action {
    //gameController.restart()
    Ok(views.html.ttfe(gameController, "About"))
  }

  def gridToJson = Action {
    //gameController.restart()
    Ok(views.html.ttfe(gameController, "JSON"))
    //Ok(gameController.toJson)
  }

}