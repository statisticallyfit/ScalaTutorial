package Eckel_AtomicScala.traits.Composition

/**
  *
  */
object Exercise1_2_ServingPrepKitchen {

     trait Building
     trait Room
     trait Storage
     trait Sink
     trait Store[T]
     trait Cook[T]
     trait Clean[T]
     trait Food extends Store[Food]
          with Clean[Food] with Cook[Food]
     trait Utensils extends Store[Utensils]
          with Clean[Utensils] with Cook[Utensils]

     trait Kitchen extends Room {
          /*val storage: Storage
          val sinks: Vector[Sink]
          val food: Food
          val utensils: Utensils*/
          def serveFood: Boolean
          def prepFood: Boolean
     }

     //help is this feasible? How to provide trait-type arguments?
     class ServingKitchen(/*st: Storage, ss: Vector[Sink], us: Utensils, f: Food*/) extends Kitchen {
          override def serveFood = true
          override def prepFood = false
     }

     class PrepKitchen(/*st: Storage, ss: Vector[Sink], us: Utensils, f: Food*/) extends Kitchen {/*
          val storage = st
          val sinks = ss
          val food = f
          val utensils = us*/
          override def serveFood = false
          override def prepFood = true
     }

     class SimpleKitchen extends Kitchen {
          override def serveFood = true
          override def prepFood = true
     }

     trait House extends Building {
          val kitchens: Vector[Kitchen]
     }

     def main(args: Array[String]) {

          val serving = new ServingKitchen
          assert(serving.serveFood)
          assert(!serving.prepFood)

          val prep = new PrepKitchen
          assert(prep.prepFood)
          assert(!prep.serveFood)

          val simple = new SimpleKitchen
          assert(simple.prepFood)
          assert(simple.serveFood)
     }
}
