package Blogs.AdHocPolyBlogTryOuts.petExample

import java.awt.Color

/**
  *
  */
object Subtyping_Pet_FBounded {



     trait Pet[A <: Pet[A]] { this: A => //self type

          def name: String
          def rename(newName: String): A
     }


     case class Fish(name: String, age: Int) extends Pet[Fish] {
          def rename(newName: String): Fish = this.copy(name = newName)
     }

     //note: this would work (bad!) without the this: A selftype in trait
     /*case class Kitty(name: String, color: Color) extends Pet[Fish]{
          def rename(newName: String): Fish = new Fish(newName, 42)
     }*/
     case class Kitty(name: String, color: Color) extends Pet[Kitty]{
          def rename(newName: String): Kitty = new Kitty(newName, Color.BLUE)
     }

     // ------------------------------------------------------------------------
     //note: ok we got this far, but the following problem still exists:
     class Mammal(val name: String) extends Pet[Mammal] {
          def rename(newName: String): Mammal = new Mammal(newName)
     }

     class Monkey(name: String) extends Mammal(name) //Monkey is actually a Pet[Mammal]



     def esquire[A <: Pet[A]](a: A): A = a.rename(a.name + ", Esq.")


     def main(args: Array[String]) {
          val a = Fish("Jimmy", 2)
          Console.println(a)

          val b = a.rename("Bob")
          Console.println(b)

          Console.println(esquire(a))
     }
}
