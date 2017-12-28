package Eckel_AtomicScala.classes.Inheritance



object Exercise3_Bicycle extends  App {

     class Cycle (wheels: Int = 2) {
          def ride = "Riding"
     }

     class Bicycle (val wheels: Int = 2) extends Cycle(wheels)


     val c = new Cycle
     assert(c.ride == "Riding", "Test 1 failed")

     val b = new Bicycle
     assert(b.ride == "Riding", "Test 2 failed")
     assert(b.wheels == 2, "Test 3 failed")
}