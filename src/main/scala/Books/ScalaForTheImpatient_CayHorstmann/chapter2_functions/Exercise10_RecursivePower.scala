package Books.ScalaForTheImpatient_CayHorstmann.chapter2_functions

/**
  *
  */
object Exercise10_RecursivePower {

     def exponent(base: Double, n: Int): Double = {
          if (n == 0) 1
          else {
               if (n > 0) {
                    if ( n % 2 == 0 && n > 2) {
                         exponent(exponent(base, n/2), 2)
                    } else {
                         base * exponent(base, n - 1)
                    }
               } else 1 / exponent(base, -n)
          }
     }

     def main(args: Array[String]) {

          println("power 2^3: " + exponent(16, 2).toInt)
     }
}
