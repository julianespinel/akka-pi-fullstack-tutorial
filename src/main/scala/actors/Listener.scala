package main.scala.actors

import akka.actor._
import scala.concurrent.duration._
import main.scala.messages._

class Listener extends Actor {

  def receive = {

    case PiApproximation(pi, duration, decimalDigits) =>
      println("Pi approximation:\t%s\nCalculation time:\t%s\nDecimal digits:\t\t%s\n".format(pi, duration, decimalDigits))
      context.system.shutdown()
  }
}
