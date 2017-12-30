package Books.Horstmann_ScalaImpatient.chapter2_functions

/**
  *
  */

object Exercise6_ProductStringLetters {

     // @todo: how to multiply string letter chars functionally?
     def productUsingFunctionalApproach(str: String): Long = {
          str.map(_.toLong).reduceLeft(_ * _)
          // or
          // str.map(_*1).product
     }

     def productUsingForLoop(str: String): Long = {
          var cumProduct = 1L
          for(i <- 0 until (str.length)) {
               println(str.charAt(i) + " = " + str.charAt(i).toLong)
               cumProduct *= str.charAt(i).toLong
          }
          cumProduct
     }

     // exercise 9
     def recursiveProduct(args: String) : Long = {
          if (args.length == 0)
               1
          else
               args.head * recursiveProduct(args.tail)
     }

     def main(args: Array[String]) {

          val str = "Hello"
          // @todo: why not correct answer?
          println("easy way: " + str.product.toLong) // the easy way
          println("for product: " + productUsingForLoop(str))
          println("reduceLeft product: " + productUsingFunctionalApproach(str))
          println("recursive product: " + recursiveProduct(str))
     }
}
