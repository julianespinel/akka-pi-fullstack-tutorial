package main.scala.actors

import akka.actor._
import main.scala.messages._

class Worker extends Actor {

  def receive = {

    case Calculate(start, elementsPerCalculation) =>
      sender ! CalculationResult(calculatePiFunctionalFor(start, elementsPerCalculation, 0, 0.0))
  }

  def calculatePiFunctionalFor(start: Int, elementsPerCalculation: Int, counter: Int, 
    accumulator: Double): Double =  counter match {

      case `elementsPerCalculation` => accumulator
      case counter if (counter < elementsPerCalculation) =>
        calculatePiFunctionalFor(start + 1, elementsPerCalculation, counter + 1,
          (accumulator + (4.0 * (1 - (start % 2) * 2) / (2 * start + 1))))
  }
}
