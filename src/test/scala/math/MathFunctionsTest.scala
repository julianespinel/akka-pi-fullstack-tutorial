package test.scala.math

import main.scala.math.MathFunctions
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

object MathFunctionsTest extends Properties("MathFunctionsTest") {

  // Tests for the method getNumberOfDecimalPlaces

  property("decimalCases") = {

    val case1 = 3.1415
    4 == MathFunctions.getNumberOfDecimalPlaces(case1)

    val case2 = 90.123456789
    9 == MathFunctions.getNumberOfDecimalPlaces(case2)

    val case3 = 190.12345678912345
    14 == MathFunctions.getNumberOfDecimalPlaces(case3)
  }

  property("decimalPlaces") = forAll { doubleNumber: Double =>

    val decimalPlaces = MathFunctions.getNumberOfDecimalPlaces(doubleNumber)
    val isPositive = decimalPlaces >= 0
    val isInt = decimalPlaces match {
      case n: Int => true
    }

    isPositive && isInt
  }
}
