package Books.Cay_ScalaForTheImpatient.chapter12_higherOrderFunctions

/**
  *
  */
object Exercise9_CorrespondImpl {


     def corresponds[A, B](a: Seq[A], b: Seq[B], f: (A, B) => Boolean): Boolean ={
          (a zip b).map(pair => f(pair._1, pair._2)).forall(bool => bool)
     }

     def main(args: Array[String]) {

          val input = List("peter", "piper", "picked", "a", "peck", "of", "pickled", "peppers")
          val check = input.map(_.length)
          val wrongCheck = (1 to check.length).toList

          assert(corresponds(input, check, (x:String, y:Int) => x.length == y))
          assert(!corresponds(input, wrongCheck, (x:String, y:Int) => x.length == y))
     }
}
