package Books.Eckel_AtomicScala.patternmatching.PatternMatchingWIthTypes

/**
  *
  */
object Exercise2_3_ConvertSIze {

     case class Person(name:String)

     def convertToSize(any: Any): Int ={
          any match {
               case s:String => s.length
               case n: Double => Math.round(n).toInt
               case n: Int => n
               case p:Person => 1
               case _ => 0
          }
     }

     def main(args: Array[String]) {

          assert(convertToSize(45) == 45)
          assert(convertToSize("car") == 3)
          assert(convertToSize("truck") == 5)
          assert(convertToSize(Person("Carolyn")) == 1)
          assert(convertToSize(45.6) == 46)
          assert(convertToSize(Vector(1,2,3)) == 0)
     }
}
