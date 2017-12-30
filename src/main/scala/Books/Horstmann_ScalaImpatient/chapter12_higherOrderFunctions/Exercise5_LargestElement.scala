package Books.Horstmann_ScalaImpatient.chapter12_higherOrderFunctions

/**
  *
  */
object Exercise5_LargestElement {


     def largestValue(f: Int => Int, inputs: Seq[Int]): Int ={
          val largest: Int = inputs.map(x => f(x)).max
          largest
     }

     def main(args: Array[String]) {
          Console.println(largestValue(x => 10*x - x*x, 1 to 10))
     }
}
