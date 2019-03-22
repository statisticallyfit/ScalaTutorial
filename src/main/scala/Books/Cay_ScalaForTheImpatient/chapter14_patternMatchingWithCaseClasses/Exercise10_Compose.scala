package Books.Cay_ScalaForTheImpatient.chapter14_patternMatchingWithCaseClasses

/**
  *
  */
object Exercise10_Compose {

     //help why does error pop up when uncover function type?
     def compose(f: Double => Option[Double],
                 g: Double => Option[Double])/*: Option[Double]*/ ={

          (x: Double) => f(x) match {
               case Some(x) => g(x)
               case None => None
          }
     }

     def main(args: Array[String]) {

          def f: Double => Option[Double] = x => if(x >= 0) Some(Math.sqrt(x)) else None
          def g : Double => Option[Double] = x => if(x != 1) Some(x + 2) else None
          val h = compose(f, g)

          assert(h(4).contains(4))
          assert(h(169).contains(15))
     }
}
