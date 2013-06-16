package main.scala.persistence

import com.mongodb.casbah._
import com.mongodb.casbah.commons.MongoDBObject

/*
 * Object responsible for creating the connection to the db and provide basic
 * operations over it.
 */
object DBConnector {

  val mongoClient = MongoClient()
  val mongoDB = mongoClient("pi-tutorial-db")

  def addValue(value: Double) {

    val dbo = MongoDBObject("value" -> value)
    val valuesCollection = mongoDB.getCollection("values")
    valuesCollection.insert(dbo)
  }
}
