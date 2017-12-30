package Books.Horstmann_ScalaImpatient.chapter13_collections

/**
  *
  *
  */
object Exercise7_Tupled {

     def main(args: Array[String]) {

          val prices = List(5.0, 20.0, 15.0)
          val quantities = List(10, 2, 40)
          def mult: Double => Double => Double = a => b => a * b
          Console.println(prices.zip(quantities).map(Function.tupled(_ * _)))
          //Console.println(prices.zip(quantities).map(pair => Function.tupled(pair * pair)))
          Console.println(prices.zip(quantities).map(p => p._1 * p._2)) // inelegant
          Console.println((prices,quantities).zipped.map(_ * _)) // help how does this work?
          Console.println(prices.zip(quantities).map(p => Function.uncurried(mult)(p._1, p._2)))
     }
}
