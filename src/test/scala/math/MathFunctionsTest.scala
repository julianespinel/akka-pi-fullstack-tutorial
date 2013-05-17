package test.scala.math

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object MathFunctionsTest extends Properties("MathFunctionsTest") {

  property("startsWith") = forAll { (a: String, b: String) =>
    (a+b).startsWith(a)
  }
}
