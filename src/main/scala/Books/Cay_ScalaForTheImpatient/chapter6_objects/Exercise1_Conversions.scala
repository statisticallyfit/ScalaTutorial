package Books.Cay_ScalaForTheImpatient.chapter6_objects

/**
  *
  */
object Exercise1_Conversions {

     def inchesToCentimeters(inches: Double) = inches * 2.54
     def gallonsToLiters(gallons: Double) = gallons * 3.78541
     def milesToKilometers(miles: Double) = miles * 1.60934

     def main(args: Array[String]) {
          println(inchesToCentimeters(10))
          println(gallonsToLiters(18))
          println(3)
     }
}
