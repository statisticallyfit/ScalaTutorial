package Books.Eckel_AtomicScala.classes.NamedAndDefaultArgs

// Named and Default Arguments


object Exercise6_Item extends App {


     class Item (name: String, price: Double) {

          def cost(medication: Boolean = false,
                   grocery: Boolean = false,
                   taxRate: Double = 0.10) = {

               if (medication || grocery) price
               else price + (taxRate * price)
          }
     }

     val flour = new Item(name = "flour", 4)
     assert(flour.cost(grocery = true) == 4)

     val sunscreen = new Item(name = "sunscreen", 3)
     assert(sunscreen.cost() == 3.3)

     val tv = new Item(name = "television", 500)
     assert(tv.cost(taxRate = 0.06) == 530)
}
