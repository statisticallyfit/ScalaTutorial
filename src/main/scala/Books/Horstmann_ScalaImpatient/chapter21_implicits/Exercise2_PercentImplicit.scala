package Books.Horstmann_ScalaImpatient.chapter21_implicits

import scala.language.implicitConversions


object Exercise2_PercentImplicit {

     class PercentAdder(val value: Int) {
          def +%(percent: Int): Int = value + (value * percent * 1.0 / 100).toInt //rounding down
     }

     implicit def intToPercentAdder(value:Int): PercentAdder = new PercentAdder(value)

     def main(args: Array[String]) {

          Console.println(120 +% 10)
     }
}
