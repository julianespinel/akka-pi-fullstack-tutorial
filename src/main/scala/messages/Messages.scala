package main.scala.messages

import scala.concurrent.duration._

/*
 * This file contains the messages used in the application.
 */

/*
 * A Trait to identify the messages of this application.
 */
sealed trait PiMessage

/*
 * The message to begin the calculation.
 */
case class BeginCalculation extends PiMessage

/*
 * The message to tell a worker actor the number in which should begin the 
 * calculation and the number of steps to calculate.
 */
case class Calculate(start: Int, numOfElements: Int) extends PiMessage

/*
 * The message to tell the result of a given calcultion to the maste actor.
 */
case class CalculationResult(value: Double) extends PiMessage

/*
 * The message that contains the information to display in console.
 */
case class DisplayMessage(pi: Double, duration: Duration, decimalDigits: Int) extends PiMessage

/*
 * Message to calculate Pi using only one thread.
 * The elements param represents the number of steps int the summation.
 * In this case, the summation should begin at 0.
 */
case class CalculateNotParalell(elements: Int) extends PiMessage