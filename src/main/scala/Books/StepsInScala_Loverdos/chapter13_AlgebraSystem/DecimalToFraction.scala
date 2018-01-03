package Books.StepsInScala_Loverdos.chapter13_AlgebraSystem

/**
  *
  */
object DecimalToFraction {

     def GCD(num1: Long, num2: Long): Long ={
          def innerGCD(a: Long, b:Long): Long ={
               if(b == 0) a
               else GCD(b, a % b)
          }
          innerGCD(Math.abs(num1), Math.abs(num2))
     }

     def convertToFraction(decimal:Double): String ={
          val decStr = decimal.toString
          val (whole, rest): (String, String) = decStr.splitAt(decStr.indexOf("."))
          val wholeNum: Long = whole.toLong
          val power: Long = rest.tail.length
          var fracNumer: Long = rest.tail.toLong
          var fracDenom: Long = Math.pow(10, power).toLong
          val gcd: Long = GCD(fracNumer, fracDenom)
          fracNumer /= gcd
          fracDenom /= gcd

          val top: Long = wholeNum * fracDenom + fracNumer

          if(fracDenom != 1) s"${top.toString} / ${fracDenom.toString}" else top.toString
     }


     def main(args: Array[String]) {

          println(convertToFraction(1.8839948))
          println(convertToFraction(1.33333))
          println(convertToFraction(0.003443))
          println(convertToFraction(0.25))
          println(convertToFraction(0.5))
          println(convertToFraction(5.25))
          println(convertToFraction(0.0625))
          println(convertToFraction(0.0833333333333333333333333))
          println(convertToFraction(1.0 / 13))
          println(convertToFraction(2.25))
     }
}
