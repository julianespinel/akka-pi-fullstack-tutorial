package main.scala.actors

import akka.actor._
import akka.routing.RoundRobinRouter
import main.scala.messages._
import scala.concurrent.duration._

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
          listener ! PiApproximation(pi, duration = (System.currentTimeMillis - initialTime).millis)
          context.stop(self)
        }
  }
}
