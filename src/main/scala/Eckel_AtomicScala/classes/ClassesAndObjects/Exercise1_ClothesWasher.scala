package Eckel_AtomicScala.classes.ClassesAndObjects

/**
  * Auxiliary Constructors
  */

object Exercise1_ClothesWasher extends App {

     class ClothesWasher(val modelName: String, val capacity: Double) {

          def this(modelName: String) = {
               this(modelName, 2)
          }

          def this(capacity: Double) = {
               this("Unknown", capacity)
          }

          def this() = {
               this("Unknown", 2)
          }
     }


     val w1 = new ClothesWasher
     assert(w1.capacity == 2D)
     assert(w1.modelName == "Unknown")

     // can also assign AllInOne without name tag = ...
     val w2 = new ClothesWasher(modelName = "AllInOne")
     assert(w2.capacity == 2D)
     assert(w2.modelName == "AllInOne")
}