package main.scala.actors

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import main.scala.actors._
import main.scala.messages._
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.Future
import spray.routing._

/*
 * Actor responsible for define the paths of the services.
 */
class Master extends Actor with HttpServiceActor {

  def receive = runRoute {

    path("pi") {
      
      get {
        // URL GET parameters
        parameters("workers".as[Int], "calculations".as[Int], 
          "elements".as[Int]) { (workers, calculations, elements) =>

          // val calculactor = context.actorOf(Calculactor.props(workers, calculations, elements))
          val calculactorProps = Props(new Calculactor(workers, calculations, elements))
          val calculactor = context.actorOf(calculactorProps)
          calculactor ! BeginCalculation

          complete("Hello from master actor")
        } ~
        parameters("elements".as[Int]) { (elements) =>

          val calculactorProps = Props(new Calculactor(1, 1, 1))
          val calculactor = context.actorOf(calculactorProps)

          implicit val timeout = Timeout(5 seconds)
          val future = calculactor ? CalculateNotParalell(elements)
          val result = Await.result(future, timeout.duration).asInstanceOf[Double]

          complete("sync Pi: " + result)
        }
      }
    }
  }
}