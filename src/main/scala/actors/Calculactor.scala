package main.scala.actors

import akka.actor._
import akka.routing._
import main.scala.math.MathFunctions
import main.scala.messages._
import scala.concurrent.Future
import scala.concurrent.duration._
import spray.http._

/*

// This will work in Akka 2.2

object Calculactor {

  def props(numOfWorkers: Int, numOfCalculations: Int,
    elementsPerCalculation: Int): Props = {

      Props(classOf[Calculactor], numOfWorkers, numOfCalculations,
        elementsPerCalculation)
  }
}
*/

/** 
 * This actor is responsible for: 
 *
 * 1. Create the workerRouter that will handle the workers.
 * 2. Send messages to each worker to perform a part of the calculation.
 * 3. Receive and sum the result of each calculation.
 * 4. Send the final message to the master actor in order to display the resutls.
 */
class Calculactor(numOfWorkers: Int, numOfCalculations: Int, elementsPerCalculation: Int) extends Actor {

  var pi: Double = _
  var numOfResults: Int = _
  val initialTime: Long = System.currentTimeMillis

  val workerRouter = context.actorOf(
    Props[Worker].withRouter(RoundRobinRouter(numOfWorkers)), name = "workerRouter")
  
  def receive = {

    case BeginCalculation =>
      for (i <- 0 until numOfCalculations)
        workerRouter ! Calculate(i * elementsPerCalculation, elementsPerCalculation)

    case CalculationResult(value) =>
        pi += value
        numOfResults += 1
        if (numOfResults == numOfCalculations) {
          println("pi: " + pi)
          context.stop(self)
        }
      
    case CalculateNotParalell(elements) => 
      val piResult = MathFunctions.calculatePiFunctional(0, elements)
      sender ! piResult
  }
}