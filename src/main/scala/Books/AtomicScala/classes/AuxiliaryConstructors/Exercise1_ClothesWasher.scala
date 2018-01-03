package Books.AtomicScala.classes.AuxiliaryConstructors

/**
  *
  */
object Exercise1_ClothesWasher {


     class ClothesWasher(val modelName: String, val capacity: Double) {

          def this(modelName: String){
               this(modelName, 0.0)
          }

          def this(capacity: Double) {
               this("<unknown>", capacity)
          }

          def this(){
               this("<unknown>", 0.0)
          }
     }

     def main(args: Array[String]) {

          val washer1 = new ClothesWasher
          assert(washer1.capacity == 0)
          assert(washer1.modelName == "<unknown>")

          val washer2 = new ClothesWasher("AllInOne")
          assert(washer2.capacity == 0)
          assert(washer2.modelName == "AllInOne")

          val washer3 = new ClothesWasher("AllInOne", 3.6)
          assert(washer3.capacity == 3.6)
          assert(washer3.modelName == "AllInOne")
     }
}
