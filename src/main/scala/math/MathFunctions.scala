package main.scala.math

object MathFunctions {

  /*
   * Given a Double object, returns its decimal places.
   */
  def getNumberOfDecimalPlaces(doubleNumber: Double): Int = {

    val numberCharList = doubleNumber.toString.toList

    def countDecimalPlaces(numberCharList: List[Char]): Int = numberCharList match {

      case '.' :: tail => tail.length
      case _ => countDecimalPlaces(numberCharList.tail)
    }

    countDecimalPlaces(numberCharList)
  }

  /*
   * This function calculates the given number of steps in the Pi summation.
   */
  private def calculatePiFunctionalFor(start: Int, steps: Int, counter: Int, 
    accumulator: Double): Double = counter match {
  
    case `steps` => accumulator
    case `counter` if (counter < steps) =>
      calculatePiFunctionalFor(start + 1, steps, counter + 1, 
        accumulator + (4.0 * (1 - (start % 2) * 2) / (2 * start + 1)))
  }

  /*
   * This method calculate a given number of steps in the summation.
   * The number of steps is described by the parameter steps.
   */
  def calculatePiFunctional(start: Int, steps: Int): Double = (start, steps) match {

    case (start, steps) if (start < 0 || steps <= 0) => 0.0
    case _ => calculatePiFunctionalFor(start, steps, 0, 0.0)
  }
}
