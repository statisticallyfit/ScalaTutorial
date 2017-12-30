package Books.Horstmann_ScalaImpatient.chapter12_higherOrderFunctions

/**
  *
  */
object Exercise2_LargestElement {


     def main(args: Array[String]) {

          val a = Array(34,2,5,-67,9,10,-45,31,10,-3,1,0,3,4,5)
          Console.println(a.reduceLeft((acc, y) => if(y > acc) y else acc))
          Console.println(a.reduceLeft(_ max _))
     }
}
