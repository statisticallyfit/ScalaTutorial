package Books.ScalaForTheImpatient_CayHorstmann.chapter14_patternMatchingWithCaseClasses

/**
  *
  */
object Exercise3_ArraySwap {


     def arraySwap(array: Array[Any]): Array[Any] ={
          array match {
               case Array(x, y, rest@_*) => Array(y, x, rest)
               case _ => Array()
          }
     }

     def main(args: Array[String]) {
          Console.println(arraySwap(Array(1,2)).mkString(", "))
          Console.println(arraySwap(Array(1,2,3)).mkString(", "))
          Console.println(arraySwap(Array(1,2,3,4,5)).mkString(", "))
          Console.println(arraySwap(Array(1)).mkString(", "))
     }
}
