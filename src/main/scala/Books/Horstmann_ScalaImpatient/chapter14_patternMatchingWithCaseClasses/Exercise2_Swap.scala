package Books.Horstmann_ScalaImpatient.chapter14_patternMatchingWithCaseClasses

/**
  *
  */
object Exercise2_Swap {

     def swap(a:Int, b:Int): (Int,Int) ={(b,a)}

     def main(args: Array[String]) {
          Console.println((1,2).swap)
          Console.println(swap(1,2))
     }
}
