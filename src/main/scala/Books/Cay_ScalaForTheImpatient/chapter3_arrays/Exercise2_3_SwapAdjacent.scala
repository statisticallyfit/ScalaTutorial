package Books.Cay_ScalaForTheImpatient.chapter3_arrays

/**
  *
  */
object Exercise2_3_SwapAdjacent {

     def main(args: Array[String]) {

          val array = Array(1, 2, 3, 4, 5)

          // solution1
          println("result1: " + array.sliding(2, 2).flatMap(_.reverse).toArray.mkString(", "))

          // solution 2
          println("result2: " + array.grouped(2).toArray.flatMap(_.reverse).mkString(", "))

          // solution 3
          val result3 = { // can even use '(' instead of '{'
               for (i <- 0 until array.length by 2)
                    yield
                         if (i < array.length-1){
                              Array(array(i+1), array(i))
                         } else {
                              Array(array(i))
                         }

               // @todo: understand the syntax (a, b) better (how are they declared?) and why you need
               // @ todo ... need the Array[Int] in the arguments?
          }.foldLeft (Array[Int]()) ((a, b) => a ++ b)
          println("result3: " + result3.mkString(", "))


          // solution 4
          val result4 = {
               for (i <- 0 until array.length)
                    yield
                         if (i % 2 == 0) {
                              if (i == array.length - 1)
                                   array(i)
                              else
                                   array(i + 1)
                         } else {
                              array(i - 1)
                         }
          }
          println("result4: " + result4.mkString(", "))
     }
}
