package Books.AtomicScala.collections.Sequences

/**
  *
  */
object Exercise5_6_TieSort {


     trait Contact {
          val email: String
     }

     class Person(first: String, last: String){
          override def toString = s"${first.trim} ${last.trim}"
     }

     //note the parameter 'email' is passed to trait implicitly
     class Friend(val first: String, val last: String, val email: String)
          extends Person(first, last) with Contact

     def main(args: Array[String]) {

          val friends = Vector(
               new Friend("Zach", "Smith", "zach@smith.com"),
               new Friend("Mary", "Add", "mary@add.com"),
               new Friend("Sally", "Taylor", "sally@taylor.com"),
               new Friend("Mary", "Smith", "mary@smith.com")
          )
          val lastsThenFirst = friends.sortBy(_.last).sortBy(_.first)
          assert(lastsThenFirst.toString == "Vector(Mary Add, Mary Smith, Sally Taylor, Zach Smith)")
          Console.println(lastsThenFirst.toString)

          val firstThenLast = friends.sortBy(_.first).sortBy(_.last)
          assert(firstThenLast.toString == "Vector(Mary Add, Mary Smith, Zach Smith, Sally Taylor)")
          Console.println(firstThenLast)
     }
}
