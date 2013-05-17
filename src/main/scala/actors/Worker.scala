package main.scala.actors

import akka.actor._
import main.scala.math.MathFunctions
import main.scala.messages._

/*
 * Its the actor who executes part of the calculation.
 */
class Worker extends Actor {

  def receive = {

    case Calculate(start, elementsPerCalculation) =>
      sender ! CalculationResult(MathFunctions.calculatePiFunctionalFor(start, elementsPerCalculation, 0, 0.0))
  }
}
