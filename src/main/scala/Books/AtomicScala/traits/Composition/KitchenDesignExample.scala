package Books.AtomicScala.traits.Composition

/**
  *
  */
object KitchenDesignExample {


     object House1 {
          trait Building
          trait Kitchen
          trait House extends Building with Kitchen
     }

     object House2 {
          // we may want to have more than one kitchen in the house
          trait Building
          trait Kitchen
          trait House extends Building {
               val kitchen1: Kitchen
               val kitchen2: Kitchen
          }
     }

     object House3 {
          //allowing for multiple kitchens
          trait Building
          trait Kitchen
          trait House extends Building {
               val kitchens: Vector[Kitchen]
          }
     }

     object House4 {
          //trying to express ability that kitchen can store food and utensils
          // AND store and clean utensils and cook food. But does not work.
          trait Building
          trait Food
          trait Utensils
          trait Store[T]
          trait Cook[T]
          trait Clean[T]
          trait Kitchen extends Store[Food] with Cook[Food] with Clean[Utensils]
          //Oops. Can't do this:
          // with Store[Utensils] with Clean[Food]

          trait House extends Building {
               val kitchens: Vector[Kitchen]
          }
     }


     object HouseBest {
          //assuming that not kitchen has the abilities, but the items themselves.
          //kitchen also has general purpose sink, not just for food,utensils...
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
               val storage: Storage
               val sinks: Vector[Sink]
               val food: Food
               val utensils: Utensils
          }

          trait House extends Building {
               val kitchens: Vector[Kitchen]
          }
     }
}
