package Books.Horstmann_ScalaImpatient.chapter3_arrays

/**
  *
  */
object Exercise6_ReverseSort {

     def main(args: Array[String]) {

          val array = Array(1, -10, 8, 4, -9, 3, 2, -7, 3, -8, 6, 0, 20)

          // solution 1
          val result1 = array.sorted.reverse
          println("result1: " + result1.mkString(", "))

          // solution 3
          val result2 = array.sortWith(_ > _)
          println("result2: " + result2.mkString(", "))

          // solution 4
          val result3 = array.sortWith(_ < _).reverse
          println("result3: " + result3.mkString(", "))
     }
}
