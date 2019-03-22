package Books.Cay_ScalaForTheImpatient.chapter12_higherOrderFunctions

import scala.io.StdIn
/**
  *
  */
object Exercise10_Unless {

     def factorial(limit: Int): Int ={
          (0 to limit).foldLeft(0)((acc, y) => if(acc == 0) 1 else acc * y)
     }

     def unless(condition: => Boolean)(elseMsg: String)(block: => Unit): Unit ={
          if(!condition){ block } else Console.println("ERROR: " + elseMsg)
     }

     def main(args: Array[String]) {

          Console.print("Enter input: ")
          val input: Int = StdIn.readInt()

          unless(input < 0)("negative input"){
               Console.println("Factorial: " + factorial(input))
          }
     }
}
