package Books.Horstmann_ScalaImpatient.chapter3_arrays

/**
  *
  */

object Exercise1_RandomRange {

     // @todo: why error when returning type IndexedSeq[Int]? why type mismatch?????

     def generateRandomNumbers(howMany: Int): IndexedSeq[Int] = {

          val numbers = {
               for (i <- 0 until howMany) //range from 0 to (howMany-1)
                    yield (Math.random() * howMany).toInt
          }
          numbers
     }

     def main(args: Array[String]) {

          println(generateRandomNumbers(10))
     }
}
