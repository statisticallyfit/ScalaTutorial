package Eckel_AtomicScala.traits.Composition

/**
  *
  */
object Exercise3_Utensils {


     case class Knife(name: String)
     case class Fork(name: String)
     case class Spoon(name: String)
     class Utensils

     case class Spatula(name: String)
     case class Flatware(names: String*)

     class PrepUtensils extends Utensils {
          val spatula = new Spatula("Turning")
          val chefKnife = new Knife("Chef")
          val butterKnife = new Knife("Butter")
          val spoon = new Spoon("Serving")
          override def toString = "PrepUtensils"
     }

     class ServeUtensils extends Utensils {
          val flatware = new Flatware("Fork", "Knife", "Spoon")
          val servingSpoon = new Spoon("Serving")
          override def toString = "ServeUtensils"
     }

     trait Kitchen {
          def serveFood: Boolean
          def prepFood: Boolean
          val utensils: Vector[Utensils]
     }

     class ServingKitchen extends Kitchen {
          override def serveFood: Boolean = true
          override def prepFood: Boolean = false
          val utensils = Vector(new ServeUtensils)
     }

     class PrepKitchen extends Kitchen {
          override def serveFood: Boolean = false
          override def prepFood: Boolean = true
          val utensils = Vector(new PrepUtensils)
     }

     class SimpleKitchen extends Kitchen {
          override def serveFood: Boolean = true
          override def prepFood: Boolean = true
          val utensils = Vector(new ServeUtensils, new PrepUtensils)
     }


     def main(args: Array[String]) {
          val serving = new ServingKitchen
          assert(serving.serveFood)
          assert(!serving.prepFood)
          assert(serving.utensils.toString == "Vector(ServeUtensils)")

          val prep = new PrepKitchen
          assert(!prep.serveFood)
          assert(prep.prepFood)
          assert(prep.utensils.toString == "Vector(PrepUtensils)")

          val simple = new SimpleKitchen
          assert(simple.serveFood)
          assert(simple.prepFood)
          assert(simple.utensils.toString == "Vector(ServeUtensils, PrepUtensils)")
     }
}
