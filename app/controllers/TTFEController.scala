package controllers

import javax.inject._
import play.api.mvc._
import de.htwg.se.ttfe.TTFE
import de.htwg.se.ttfe.model.{Cell, Field}
import play.api.libs.json._

import play.api.libs.streams.ActorFlow
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.actor._


@Singleton
class TTFEController @Inject()(cc: ControllerComponents) (implicit system: ActorSystem, mat: Materializer) extends AbstractController(cc) {
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

  def gridToJsonRet : String = {
    implicit val fieldWrites = new Writes[Field] {
      def writes(field: Field) = Json.obj(
        "size" -> field.grid.size,
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
    return json.toString
  }

  def ttfePolymer = Action {
    Ok(views.html.ttfePolymer())
  }

  def socket = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef { out =>
      println("Connect received")
      TTFESocketActorFactory.create(out)
    }
  }

  object TTFESocketActorFactory {
    def create(out: ActorRef) = {
      Props(new TTFEWebSocketActor(out))
    }
  }

  class TTFEWebSocketActor(out: ActorRef) extends Actor {
    sendJsonToClient

    while(true){
      Thread.sleep(1000)
      sendJsonToClient
    }

    def receive = {
      case msg: String =>
        out ! (gridToJson.toString)
        println("Sent Json to Client"+ msg)
    }

    def sendJsonToClient = {
      println("Received event from Controller")
      out ! (gridToJsonRet)
    }
  }

}