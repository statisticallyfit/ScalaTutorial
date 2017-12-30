package Books.Horstmann_ScalaImpatient.chapter12_higherOrderFunctions

/**
  *
  */
object Exercise6_LargestInput {


     //gets the input at which function result was largest.
     def indexInput(f: Int => Int, inputs: Seq[Int]): Int ={
          val largest: Int = inputs.map(x => f(x)).max
          val index: Int = inputs.map(x => f(x)).indexOf(largest)
          inputs(index)
     }

     def main(args: Array[String]) {
          Console.println(indexInput(x => 10*x - x*x, 1 to 10))
     }
}
