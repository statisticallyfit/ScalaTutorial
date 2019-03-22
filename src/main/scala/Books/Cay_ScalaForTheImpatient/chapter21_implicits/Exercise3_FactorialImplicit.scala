package Books.Cay_ScalaForTheImpatient.chapter21_implicits

import scala.language.implicitConversions
import scala.language.postfixOps


object Exercise3_FactorialImplicit {


     class FactorialWrapper(val value:Int){
          require(value > 0)
          def ! = (1 to value).product
     }

     implicit def intToFactorial(value:Int): FactorialWrapper = new FactorialWrapper(value)

     def main(args: Array[String]) {

          Console.println(5!)
          Console.println(8!)
     }

}
