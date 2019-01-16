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
import akka.actor.{ActorSystem, _}
import akka.stream.Materializer
import com.mohiva.play.silhouette.api.Silhouette
import com.mohiva.play.silhouette.api.actions.SecuredRequest
import org.webjars.play.WebJarsUtil
import play.api.i18n.I18nSupport
import utils.auth.DefaultEnv

import scala.concurrent.Future

@Singleton
class TTFEController @Inject() (
                                   components: ControllerComponents,
                                   silhouette: Silhouette[DefaultEnv]
                                 )(
                                   implicit
                                   webJarsUtil: WebJarsUtil,
                                   assets: AssetsFinder,
                                   system: ActorSystem,
                                   mat: Materializer
                                 ) extends AbstractController(components) with I18nSupport {


  val gameController = TTFE.controller
  def ttfeAsText =  gameController.fieldToString
  var update = false



  def ttfe = silhouette.SecuredAction.async { implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
    Future.successful(Ok(views.html.ttfe(gameController, "test", request.identity)))
  }

  def restart = silhouette.SecuredAction.async { implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
    gameController.restart()
    Future.successful(Ok(views.html.ttfe(gameController, "test", request.identity)))
}

  def move(direction: String) = silhouette.SecuredAction.async { implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
    gameController.moveDirection(direction)
    update = true
    Future.successful(Ok(views.html.ttfe(gameController, "move", request.identity)))
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
    //sendJsonToClient

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