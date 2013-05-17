package main.scala.actors

import akka.actor._
import akka.routing.RoundRobinRouter
import main.scala.math.MathFunctions
import main.scala.messages._
import scala.concurrent.duration._

/*
 * This actor is the main actor of the application. 
 *
 * It is responsible for:
 * 1. Begin the calculation.
 * 2. Create the workerRouter that will handle the workers.
 * 3. Receive and sum the result of each calculation.
 * 4. Send the final message to the listener in order to display the resutls.
 */
class Master(numOfWorkers: Int, numOfCalculations: Int, elementsPerCalculation: Int, listener: ActorRef) extends Actor {

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
          listener ! DisplayMessage(pi, duration = (System.currentTimeMillis - initialTime).millis,
            MathFunctions.getNumberOfDecimalPlaces(pi))
          context.stop(self)
        }
  }
}
