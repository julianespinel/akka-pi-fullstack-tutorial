package test.scala.actors

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object WorkerTest extends Properties("WorkerTest") {

  property("startsWith") = forAll { (a: String, b: String) =>
    (a+b).startsWith(a)
  }
}
