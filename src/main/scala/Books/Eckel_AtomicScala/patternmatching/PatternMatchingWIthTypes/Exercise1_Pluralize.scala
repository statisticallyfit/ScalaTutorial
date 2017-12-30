package Books.Eckel_AtomicScala.patternmatching.PatternMatchingWIthTypes

/**
  *
  */
object Exercise1_Pluralize {


     case class Person(name: String)

     def plus(arg: Any): Any = {
          arg match {
               case s:String => s + "s"
               case i:Int => i + 1
               case p:Person => p.toString + " + guest"
               case _ => "Unknown"
          }
     }

     def main(args: Array[String]) {

          assert(plus("car") == "cars")
          assert(plus(1) == 2)
          assert(plus(Person("Mary")) == "Person(Mary) + guest")
     }
}
