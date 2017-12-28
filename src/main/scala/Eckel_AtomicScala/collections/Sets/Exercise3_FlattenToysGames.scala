package Eckel_AtomicScala.collections.Sets

/**
  *
  */
object Exercise3_FlattenToysGames {


     // note flattens everything regardless of how many levels there are
     //def flattenAll(stairs: Set[Any]): Set

     def main(args: Array[String]) {

          val box1 = Set("shoes", "clothes")
          val box2 = Set("toys", "dishes")
          val box3 = Set("toys", "games", "books")
          val attic = Set(box1, box2)
          val basement = Set(box3)
          val house = Set(attic, basement)

          assert(attic.flatten.toString == "Set(shoes, clothes, toys, dishes)")
          assert(basement.flatten.toString == "Set(toys, games, books)")
          assert(house.flatMap(ss => ss.flatten).toString == "Set(dishes, clothes, books, shoes, games, toys)")
     }

}
