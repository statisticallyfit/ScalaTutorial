package Books.ScalaForTheImpatient_CayHorstmann.chapter21_implicits

import Books.lib._
import scala.language.implicitConversions



object Exercise5_FractionImplicit {


     def smaller[T](a: T, b: T)(implicit ord: T => Ordered[T])
     = if (a < b) a else b

     class RichFraction(val f: Fraction) extends Ordered[Fraction] {
          def compare(that: Fraction): Int = {
               if (this.f.denom == that.denom) {
                    this.f.numer - that.numer
               } else {
                    val (n1, n2, d) = Utils.toCommonDenominator(this.f, that)
                    n1 - n2
               }
          }
     }

     implicit def fractionToRichFraction(f: Fraction): RichFraction = new RichFraction(f)

     def main(args: Array[String]) {

          //in order to use smaller to compare fractions, we declared richfrac class in order to extend
          // the Ordered[T] type so that we can pass the frac args to smaller(). Also had implicit conversion
          assert(smaller(Fraction(1, 2), Fraction(3, 4)) == Fraction(1, 2))
     }
}
