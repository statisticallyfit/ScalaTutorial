package Books.Cay_ScalaForTheImpatient.chapter12_higherOrderFunctions

/**
  *
  */
object Exercise1_RangeFunction {

     def values(f: Int => Int, low: Int, high: Int): List[(Int, Int)] ={
          (low to high).zip((low to high).map(x => f(x))).toList
     }

     def main(args: Array[String]) {

          Console.println(values(x => x * x, -5, 5))
          values(x => x * x, -5, 5).foreach(println)
     }
}
