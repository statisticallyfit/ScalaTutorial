package Books.AtomicScala.collections.Sequences

/**
  *
  */
object Exercise1_2_3_Person {


     case class Person(first: String, last: String, email: String){
          val fullName: String = first.trim + " " + last.trim
     }


     def main(args: Array[String]) {

          val p = Person("John", "Smith", "john@smith.com")
          assert(p.fullName == "John Smith")
          assert(p.first == "John")
          assert(p.email == "john@smith.com")

          val people = Vector(
               Person("Zach", "Smith", "zach@smith.com"),
               Person("Mary", "Add", "mary@add.com"),
               Person("Sally", "Taylor", "sally@taylor.com")
          )
          val sorted = people.sortBy(_.fullName)
          assert(sorted.toString == "Vector(" +
               "Person(Mary,Add,mary@add.com), " +
               "Person(Sally,Taylor,sally@taylor.com), " +
               "Person(Zach,Smith,zach@smith.com))"
          )

     }
}
