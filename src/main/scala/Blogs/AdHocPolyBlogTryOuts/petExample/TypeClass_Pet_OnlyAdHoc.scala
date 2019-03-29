package Blogs.AdHocPolyBlogTryOuts.petExample

import java.awt.Color

import scala.language.existentials


/**
  * NOTE: Source: https://tpolecat.github.io/2015/04/29/f-bounds.html
  */



object TypeClass_Pet_OnlyAdHoc {

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


     def esquire[A: Pet](a: A): A = {
          //implicitly[Pet[A]].rename(a, a.name + ", Esq.") // also works
          a.rename(a.name + ", Esq.") //note: using the PetOps implicits.
     }


     def main(args: Array[String]) {
          val a = Fish("Jimmy", 2)
          Console.println(a)

          val b = a.rename("Sascha")
          //note: the highlight: uses rename() from PetOps implicit class

          Console.println(b)
          Console.println(esquire(a))

          // ---------------------------
          // note: example of mapping over list
          val bob = Fish("Bob", 12)
          val thor = Kitty("Thor", Color.ORANGE)

          //the challenge: FIsh, Kitty have no common supertype so it's not
          //clear what type the list should have.
          // remember: esquire takes two types: A, Pet[A]
          // so the type must be a tuple.
          type T = (A, Pet[A]) forSome {type A}

          val petsList = List[T]( (bob, implicitly[Pet[Fish]]),  (thor, implicitly[Pet[Kitty]]) )

          Console.println("\nPets printing: ")
          Console.println(petsList.map {  case(pet, petType) => esquire(pet)(petType)  })
     }
}
