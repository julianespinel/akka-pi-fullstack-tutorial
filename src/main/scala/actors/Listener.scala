package main.scala.actors

import akka.actor._
import scala.concurrent.duration._
import main.scala.messages._

/**
 * This actor is responsible for receiving the message that contains the results
 * of the calculation.
 */
class Listener extends Actor {

  def receive = {

    case DisplayMessage(pi, duration, decimalDigits) =>
      println("Pi approximation:\t%s\nCalculation time:\t%s\nDecimal digits:\t\t%s\n".format(pi, duration, decimalDigits))
      context.system.shutdown()
  }
}
