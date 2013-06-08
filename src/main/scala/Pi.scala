package main.scala

import akka.actor._
import main.scala.actors._
import scala.concurrent._
import spray.can.server.SprayCanHttpServerApp

/*
 * The object that represents the application's entrance point.
 */
object Pi extends App with SprayCanHttpServerApp {

  val actorSystem = ActorSystem("piSystem")
  val master = actorSystem.actorOf(Props[Master], "master")

  newHttpServer(master) ! Bind(interface = "localhost", port = 8080)
}
