package Eckel_AtomicScala.classes.Inheritance



object Exercise1_Ape extends App {

     class GreatApe {
          val weight = 100.0
          val age = 12

          def display() = s"weight: $weight, age: $age"
          def vocalize() = "Grrr!"
     }

     class Bonobo extends GreatApe
     class Chimpanzee extends GreatApe
     class BonoboB extends Bonobo


     val g = new GreatApe
     assert(g.vocalize() == "Grrr!", "Test 1 failed")

     val b = new Bonobo
     assert(b.vocalize() == "Grrr!", "Test 2 failed")

     val c = new Chimpanzee
     assert(c.vocalize() == "Grrr!", "Test 3 failed")
}