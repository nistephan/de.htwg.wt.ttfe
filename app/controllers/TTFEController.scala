package controllers

import javax.inject._

import play.api.mvc._
import de.htwg.se.ttfe.TTFE

@Singleton
class TTFEController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  val gameController = TTFE.controller
  def ttfeAsText =  gameController.fieldToString

  def ttfe = Action {
    //Ok(views.html.sudoku(gameController))
    Ok(views.html.ttfe(ttfeAsText))
  }

  def left = Action {
    gameController.moveDirection("L")
    Ok(ttfeAsText)
  }

  def right = Action {
    gameController.moveDirection("R")
    Ok(ttfeAsText)
  }

  def up = Action {
    gameController.moveDirection("U")
    Ok(ttfeAsText)
  }

  def down = Action {
    gameController.moveDirection("D")
    Ok(ttfeAsText)
  }


}