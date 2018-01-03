package Books.ScalaForTheImpatient_CayHorstmann.chapter4_mapsAndTuples

import scala.io.StdIn
/**
  *
  */
object Exercise8_MinMaxArray {

     def minMaxPair(array: Array[Int]) = {
          (array.min, array.max)
     }

     def main(args: Array[String]) {
          println("Enter a line of numbers separated by one space: ")
          val s = StdIn.readLine()
          val a: Array[Int] = s.split(" ").map(_.toInt)
          println("The min-max pair is: " + minMaxPair(a))
     }
}
