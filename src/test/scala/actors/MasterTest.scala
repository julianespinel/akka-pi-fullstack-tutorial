package test.scala.actors

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object MasterTest extends Properties("MasterTest") {

  property("startsWith") = forAll { (a: String, b: String) =>
    (a+b).startsWith(a)
  }
}
