package TEMP.AdHocPolyBlogTryOuts

import java.awt.Color

/**
  *
  */
object TypeClass_Complex {


     //using the typical typeclass two-arg methods.
     trait Pet[A] {
          def name(a: A): String
          def rename(a: A, newName: String): A
     }

     // in my number class, this will have the symbols: +, - ...
     implicit class PetOps[A](a: A)(implicit ev: Pet[A]) {
          def name: String = ev.name(a)
          def rename(newName: String): A = ev.rename(a, newName)
     }


     case class Fish(name: String, age: Int)
     //companion object: building the implicit instance Pet[Fish]
     object Fish {
          implicit val FishPet = new Pet[Fish] {
               def name(a: Fish): String = a.name
               def rename(a: Fish, newName: String): Fish = a.copy(name = newName)
          }
     }

     case class Kitty(name: String, color: Color)
     object Kitty {
          implicit object KittyPet extends Pet[Kitty] {
               def name(a: Kitty) = a.name
               def rename(a: Kitty, newName: String) = a.copy(name = newName)
          }
     }
}
