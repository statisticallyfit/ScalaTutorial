package Horstmann_ScalaImpatient.chapter12_higherOrderFunctions

/**
  *
  */
object Exercise4_Factorial {

     // note help todo how does foldleft seed =1 help with anything???
     def factorial(limit: Int): Int ={
          (0 to limit).foldLeft(1)((acc, y) => if(acc == 0) 1 else acc * y)
     }

     def main(args: Array[String]) {

          (-2 to 10).map(x => factorial(x)).foreach(println)
     }
}
