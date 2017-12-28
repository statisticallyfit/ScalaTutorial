package Horstmann_ScalaImpatient.chapter4_mapsAndTuples

import scala.io.StdIn

/**
  *
  */
object Exercise9_CompareToACount {

     def lessEqualGreaterThan(values: Array[Int], v: Int) = {
          (values.count(_ < v), values.count(_ == v), values.count(_ > v))
     }

     def main(args: Array[String]) {
          println("Enter line of numbers separated with one space: ")
          val vals = StdIn.readLine().split(" ").map(_.toInt) //Source.stdin.getLines()
          println("Enter limit number: ")
          val num = StdIn.readInt()
          println(lessEqualGreaterThan(vals, num))
     }
}
