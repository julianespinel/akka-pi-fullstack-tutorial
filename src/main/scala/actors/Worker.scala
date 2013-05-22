package main.scala.actors

import akka.actor._
import main.scala.math.MathFunctions
import main.scala.messages._

/*
 * Its the actor who executes part of the calculation.
 */
class Worker extends Actor {

  def receive = {

    case Calculate(start, steps) =>
      sender ! CalculationResult(MathFunctions.calculatePiFunctional(start, steps))
  }
}
