package Eckel_AtomicScala.functions.FunctionsAsObjects

/**
  *
  */
object Exercise6_Temperate {


     def main(args: Array[String]) {

          val between = (temp: Int, low:Int, high:Int) => temp <= high && temp >= low

          assert(!between(70, 80, 90))
          assert(between(70, 60, 90))
     }

}
