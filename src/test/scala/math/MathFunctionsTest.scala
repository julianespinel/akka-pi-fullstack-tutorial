package test.scala.math

import main.scala.math.MathFunctions
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties
import org.scalacheck.Gen

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

  property("decimalPlacesForAll") = forAll { doubleNumber: Double =>

    val decimalPlaces = MathFunctions.getNumberOfDecimalPlaces(doubleNumber)
    (decimalPlaces >= 0)
  }

  // I Use this generator to produce random cases with only a few steps in order to 
  // reduce the time taken by the test. The cases generated are enough to prove the
  // correctness of the function
  val generator = Gen.choose(0, 100)
  property("calculatePiFunctional") = forAll (generator, generator) { (start: Int, steps: Int) =>

    val pi = MathFunctions.calculatePiFunctional(start, steps)
    val moreOrEqThanZero = pi >= (-4.0/3.0) // f(1)
    val lessOrEqThanFour = pi <= (4.0) // f(0)

    (moreOrEqThanZero && lessOrEqThanFour)
  }
}
