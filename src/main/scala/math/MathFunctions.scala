package main.scala.math

object MathFunctions {

  /*
   * Given a Double object, returns its decimal places.
   */
  def getNumberOfDecimalPlaces(doubleNumber: Double): Int = {

    val numberCharList = doubleNumber.toString.toList

    def countDecimalPlaces(numberCharList: List[Char]): Int = numberCharList match  {

      case '.' :: tail => tail.length
      case _ => countDecimalPlaces(numberCharList.tail)
    }

    countDecimalPlaces(numberCharList)
  }

  /*
   * This method calculate a given number of steps in the summation.
   * The number of steps is described by the parameter elementsPerCalculation.
   */
  def calculatePiFunctionalFor(start: Int, elementsPerCalculation: Int, counter: Int, 
    accumulator: Double): Double =  counter match {
	
      case `elementsPerCalculation` => accumulator
      case counter if (counter < elementsPerCalculation) =>
        calculatePiFunctionalFor(start + 1, elementsPerCalculation, counter + 1,
          (accumulator + (4.0 * (1 - (start % 2) * 2) / (2 * start + 1))))
  }
}
