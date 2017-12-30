package Books.Eckel_AtomicScala.collections.Sets

/**
  *
  */
object Exercise1_2_Grocery {

     val allMeats = Set("beef", "chicken", "fish", "mussel", "turkey", "pork", "goat", "crab")

     val allFruits = Set("apple", "orange", "banana", "kiwi", "dragonfruit", "lychee", "lemon",
          "lime", "pear", "pomegranate", "melon", "watermelon", "raspberry", "blueberry", "blackberry",
          "strawberry", "mango", "coconut", "papaya", "starfruit", "date", "fig", "prune")

     val allVegetables = Set("turnip", "pumpkin", "tomato", "radish", "lettuce", "squash", "beans",
          "peas", "carrots", "sweet potatoes", "asparagus", "spinach", "broccoli", "cabbage", "cauliflower",
          "kidney beans", "black beans", "tofu", "alfalfa", "kale")

     val allVeganProtein = Set("kidney beans", "black beans", "tofu")


     def roundThree(num: Double): Double ={
          Math.round(num * 1000.0) / 1000.0
     }

     def percentMeat(g: Set[String]): Double ={
          100.0 * (g intersect allMeats).size / g.size
     }

     def percentVegetables(g: Set[String]): Double ={
          100.0 * (g intersect allVegetables).size / g.size
     }

     def percentFruits(g: Set[String]): Double ={
          100.0 * (g intersect allFruits).size / g.size
     }

     def percentOther(g: Set[String]): Double ={
          100.0 * (g -- (allFruits | allVegetables | allMeats)).size / g.size
     }

     def percentProtein(g: Set[String]): Double ={
          100.0 * (g intersect (allMeats | allVeganProtein)).size / g.size
     }

     def percentVeganProtein(g: Set[String]): Double ={
          100.0 * (g intersect allVeganProtein).size / g.size
     }


     def main(args: Array[String]) {

          val fruits = Set("apple", "orange", "banana", "kiwi")
          val vegetables = Set("beans", "peas", "carrots", "sweet potatoes",
               "asparagus", "spinach")
          val meats = Set("beef", "chicken")
          val groceryCart = Set("pretzels") ++ fruits ++ vegetables ++ meats

          assert(roundThree(percentMeat(groceryCart)) == 15.385)
          assert(roundThree(percentVegetables(groceryCart)) == 46.154)
          assert(roundThree(percentFruits(groceryCart)) == 30.769)
          assert(roundThree(percentOther(groceryCart)) == 7.692)


          val veganProtein = Set("kidney beans", "black beans", "tofu")
          val anotherGroceryCart = Set("apple",
               "pretzels", "bread", "orange", "beef",
               "beans", "asparagus", "sweet potatoes",
               "kidney beans", "black beans")

          assert(roundThree(percentMeat(anotherGroceryCart)) == 10.0)
          assert(roundThree(percentProtein(anotherGroceryCart)) == 30.0)
          assert(roundThree(percentVeganProtein(anotherGroceryCart)) == 20.0)
     }
}
