package main.scala.messages

import scala.concurrent.duration._

sealed trait PiMessage
case class PiApproximation(pi: Double, duration: Duration) extends PiMessage
case class BeginCalculation extends PiMessage
case class Calculate(start: Int, numOfElements: Int) extends PiMessage
case class CalculationResult(value: Double) extends PiMessage
