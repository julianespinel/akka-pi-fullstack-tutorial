package main.scala

import akka.actor._
import main.scala.actors._
import main.scala.messages._
import spray.can.server.SprayCanHttpServerApp
import spray.routing.SimpleRoutingApp

/*
 * The object that represents the application's entrance point.
 */
object Pi extends App with SimpleRoutingApp {

  val numOfWorkers = 10
  val numOfCalculations = 1000
  val elementsPerCalculation = 120

  startServer(interface = "localhost", port = 8080) {

    path("hello") {
      get {
        complete("hello sprayio")
        // calculate(numOfWorkers, numOfCalculations, elementsPerCalculation)
      }
    }
  }

  def calculate(numOfWorkers: Int, numOfCalculations: Int, elementsPerCalculation: Int) = {
    
    val system = ActorSystem("PiSystem")
    val listener = system.actorOf(Props[Listener], name = "listener")
    val master = system.actorOf(
      Props(new Master(numOfWorkers, numOfCalculations, elementsPerCalculation, listener)),
      name = "master")

    master ! BeginCalculation
  }
  
  // calculate(numOfWorkers, numOfCalculations, elementsPerCalculation)
}
