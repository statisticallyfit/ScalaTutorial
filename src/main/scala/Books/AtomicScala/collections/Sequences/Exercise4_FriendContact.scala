package Books.AtomicScala.collections.Sequences


/**
  *
  */
object Exercise4_FriendContact {


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
               new Friend("Sally", "Taylor", "sally@taylor.com")
          )
          val sorted = friends.sortBy(_.email)
          assert(sorted.toString == "Vector(Mary Add, Sally Taylor, Zach Smith)")
     }
}
