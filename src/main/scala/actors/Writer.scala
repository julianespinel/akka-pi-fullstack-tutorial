package main.scala.actors

import akka.actor._
import main.scala.math.MathFunctions
import main.scala.messages._
import main.scala.persistence.DBConnector

/*
 * Actor responsible for store values into the db.
 */ 
class Writer extends Actor {

  def receive = {
    
    case CalculationResult(value) => saveValue(value)
  }

  def saveValue(value: Double) {

    DBConnector.addValue(value)
  }
}
