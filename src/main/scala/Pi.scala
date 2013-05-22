package main.scala

import akka.actor._
import main.scala.messages._
import main.scala.actors._

/*
 * The object that represents the application's entrance point.
 */
object Pi extends App {

  val numOfWorkers = 10
  val numOfCalculations = 1000
  val elementsPerCalculation = 120

  def calculate(numOfWorkers: Int, numOfCalculations: Int, elementsPerCalculation: Int) {
    
    val system = ActorSystem("PiSystem")
    val listener = system.actorOf(Props[Listener], name = "listener")

    val master = system.actorOf(
      Props(new Master(numOfWorkers, numOfCalculations, elementsPerCalculation, listener)),
      name = "master")

    master ! BeginCalculation
  }
  
  calculate(numOfWorkers, numOfCalculations, elementsPerCalculation)
}
