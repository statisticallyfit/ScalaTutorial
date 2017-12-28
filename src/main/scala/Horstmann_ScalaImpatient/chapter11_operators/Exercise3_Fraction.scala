package Horstmann_ScalaImpatient.chapter11_operators

import lib.Fraction

/**
  *
  */
object Exercise3_Fraction {

     def main(args: Array[String]) {

          assert(Fraction(2,3) * Fraction(1,2) == Fraction(1,3))
          assert(Fraction(10,24) == Fraction(5, 12))
          assert(Fraction(10, 24) * Fraction(5,12) == Fraction(25, 144))
          assert(Fraction(2, 3) + Fraction(5, 8) == Fraction(31, 24))
          assert(Fraction(1, 2) - Fraction(5, 6) == Fraction(-1, 3))
          assert(Fraction(4, -5) + Fraction(7, 8) == Fraction(3, 40))
          assert(Fraction(7, -8) + Fraction(4, 5) == Fraction(-3, 40))
          assert(Fraction(7, -8) - Fraction(4, 5) == Fraction(-67, 40))
          assert(Fraction(7, -8) - Fraction(-4, 5) == Fraction(-3, 40))
     }
}
