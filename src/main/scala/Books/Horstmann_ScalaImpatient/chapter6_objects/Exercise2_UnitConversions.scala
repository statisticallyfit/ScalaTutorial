package Books.Horstmann_ScalaImpatient.chapter6_objects

import scala.Predef._
/**
  *
  */
object Exercise2_UnitConversions {

     class UnitConversion (factor: Double) {
          def convert (valueToConvert: Double): Double = factor * valueToConvert
     }

     object InchesToCentimeters extends UnitConversion(2.54)
     object GallonsToLiters extends UnitConversion(3.78541)
     object MilesToKilometers extends UnitConversion(1.60934)


     def main(args: Array[String]) {
          println("inch   -> cm:     " + InchesToCentimeters.convert(1.0))
          println("gallon -> liters: " + GallonsToLiters.convert(1.0))
          println("miles  -> km:     " + MilesToKilometers.convert(1.0))
     }
}
