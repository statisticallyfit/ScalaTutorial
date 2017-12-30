package Books.Horstmann_ScalaImpatient.chapter12_higherOrderFunctions

/**
  *
  */
object Exercise8_Corresponds {


     /**
       * def corresponds[B](that: Seq[B])(p: (A, B) => Boolean): Boolean
      */
     def lengthCorrespond(input: List[String], lengthCheck: List[Int]): Boolean ={
          input.corresponds(lengthCheck)((inp, len) => inp.length == len)
          //input.corresponds(lengthCheck)(_.length == _)
     }


     def main(args: Array[String]) {
          val input = List("peter", "piper", "picked", "a", "peck", "of", "pickled", "peppers")
          val check = input.map(_.length)
          val wrongCheck = (1 to check.length).toList

          assert(lengthCorrespond(input, check))
          assert(!lengthCorrespond(input, wrongCheck))
     }
}
