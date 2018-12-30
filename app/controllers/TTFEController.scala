package controllers

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes
import javax.inject._
import play.api.mvc._
import de.htwg.se.ttfe.TTFE
import de.htwg.se.ttfe.model.{Cell, Field}
import play.api.libs.json._

//case class Field(size: Int)

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

  def move(direction: String) = Action {
    gameController.moveDirection(direction)
    Ok(views.html.ttfe(gameController, "move: " + direction))
  }


  def gridToJson = Action {
    implicit val fieldWrites = new Writes[Field] {
      def writes(field: Field) = Json.obj(
        "size" ->field.grid.size,
        "score" -> field.score,
        "cells" -> Json.arr(
          Json.obj(
            "row" -> 0,
            "col" -> 0,
            "value" -> field.grid(0)(0).value
          ),
        Json.obj(
          "row" -> 0,
          "col" -> 1,
          "value" -> field.grid(0)(1).value
        ),
          Json.obj(
            "row" -> 0,
            "col" -> 2,
            "value" -> field.grid(0)(2).value
          ),
          Json.obj(
            "row" -> 0,
            "col" -> 3,
            "value" -> field.grid(0)(3).value
          ),
          Json.obj(
            "row" -> 1,
            "col" -> 0,
            "value" -> field.grid(1)(0).value
          ),
          Json.obj(
            "row" -> 1,
            "col" -> 1,
            "value" -> field.grid(1)(1).value
          ),
          Json.obj(
            "row" -> 1,
            "col" -> 2,
            "value" -> field.grid(1)(2).value
          ),
          Json.obj(
            "row" -> 1,
            "col" -> 3,
            "value" -> field.grid(1)(3).value
          ),
          Json.obj(
            "row" -> 2,
            "col" -> 0,
            "value" -> field.grid(2)(0).value
          ),
          Json.obj(
            "row" -> 2,
            "col" -> 1,
            "value" -> field.grid(2)(1).value
          ),
          Json.obj(
            "row" -> 2,
            "col" -> 2,
            "value" -> field.grid(2)(2).value
          ),
          Json.obj(
            "row" -> 2,
            "col" -> 3,
            "value" -> field.grid(2)(3).value
          ),
          Json.obj(
            "row" -> 3,
            "col" -> 0,
            "value" -> field.grid(3)(0).value
          ),
          Json.obj(
            "row" -> 3,
            "col" -> 1,
            "value" -> field.grid(3)(1).value
          ),
          Json.obj(
            "row" -> 3,
            "col" -> 2,
            "value" -> field.grid(3)(2).value
          ),
          Json.obj(
            "row" -> 3,
            "col" -> 3,
            "value" -> field.grid(3)(3).value
          ))
      )
    }

    val json = Json.toJson(gameController.field)

    Ok(json)
  }

}