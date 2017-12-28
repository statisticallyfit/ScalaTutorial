package Eckel_AtomicScala.classes.CaseClasses

/**
  *
  */
object Exercise3_Dogs {

     case class Dog(name: String, breed: String)


     def main(args: Array[String]) {

          val dogs = Vector(
               Dog("Fido", "Golden Lab"),
               Dog("Ruff", "Alaskan Malamute"),
               Dog("Fifi", "Miniature Poodle")
          )

          assert(dogs(0).toString == "Dog(Fido,Golden Lab)")
          assert(dogs(1).toString == "Dog(Ruff,Alaskan Malamute)")
          assert(dogs(2).toString == "Dog(Fifi,Miniature Poodle)")
     }

}
