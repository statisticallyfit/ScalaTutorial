package Eckel_AtomicScala.classes.AuxiliaryConstructors

/**
  *
  */
object Exercise4_Overload {


     class ClothesWasher(val modelName: String = "<unknown>",
                         val capacity: Double=0.0){


          def wash(): String = "Simple wash"

          def wash(bleach: Int, fabricSoftener: Int): String ={
               s"Wash used $bleach bleach and $fabricSoftener fabric softener"
          }
     }

     def main(args: Array[String]) {
          val washer = new ClothesWasher()
          assert(washer.wash() == "Simple wash")
          assert(washer.wash(2, 1) == "Wash used 2 bleach and 1 fabric softener")
     }
}
