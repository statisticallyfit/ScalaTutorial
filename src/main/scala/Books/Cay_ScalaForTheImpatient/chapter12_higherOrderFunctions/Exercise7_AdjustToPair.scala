package Books.Cay_ScalaForTheImpatient.chapter12_higherOrderFunctions

/**
  *
  */
object Exercise7_AdjustToPair {

     def adjustToPair(f: (Int, Int) => Int)(pair: (Int, Int)): Int ={
          f(pair._1, pair._2)
     }

     def main(args: Array[String]) {
          Console.println(adjustToPair(_ * _)((6, 7)))
          Console.println(adjustToPair(_ - _)((9, 45)))
          Console.println(adjustToPair(_ + _)((9, 7)))

          val pairs = (1 to 10).zip(11 to 20)
          Console.println(pairs.map(adjustToPair(_ + _)))
     }

}
