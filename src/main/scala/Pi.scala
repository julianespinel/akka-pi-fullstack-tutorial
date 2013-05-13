package main.scala

import akka.actor._
import main.scala.messages._
import main.scala.actors._

object Pi extends App {

  val numOfWorkers = 1000
  val numOfCalculations = 10000000
  val elementsPerCalculation = 1000

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
