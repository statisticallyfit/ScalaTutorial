package Horstmann_ScalaImpatient.chapter12_higherOrderFunctions

/**
  *
  */
object Exercise3_Factorial {

     def factorial(limit: Int): Int ={
          (0 to limit).reduceLeft((acc, y) => if(acc == 0) 1 else acc * y)
     }

     def main(args: Array[String]) {

          (0 to 10).map(x => factorial(x)).foreach(println)
          Console.println(factorial(-3))
     }
}
