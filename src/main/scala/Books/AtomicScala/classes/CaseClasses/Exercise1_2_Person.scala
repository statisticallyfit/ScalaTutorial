package Books.AtomicScala.classes.CaseClasses

/**
  *
  */
object Exercise1_2_Person {


     case class Person(first: String, last: String, email: String)

     def main(args: Array[String]) {

          val p = Person("Jane", "Smile", "jane@smile.com")
          assert(p.first == "Jane")
          assert(p.last == "Smile")
          assert(p.email == "jane@smile.com")


          val people = Vector(
               Person("Georgie", "Dove", "georgie@dove.com"),
               Person("Jessica", "Mays", "jessica@mays.com"),
               Person("Susan", "Peterson", "susan@peterson.com")
          )

          assert(people(0).toString == "Person(Georgie,Dove,georgie@dove.com)")
          assert(people(1).toString == "Person(Jessica,Mays,jessica@mays.com)")
          assert(people(2).toString == "Person(Susan,Peterson,susan@peterson.com)")
     }
}
