package TEMP.AdHocPolyBlogTryOuts.TypeClassApproach

// note: Source: https://medium.com/@sinisalouc/ad-hoc-polymorphism-and-type-classes-442ae22e5342


import scala.language.implicitConversions


//note: typeclasses are not an existing structure in Scala, but they are in Haskell.
// note: So we need to make the typelcass by packing together a trait (interface) and
// note: implementations of that interface for concrete types.

trait Appendable[A] {
     def append(a: A, b: A): A
}

object Appendable { // companion object
     implicit val appendableInt = new Appendable[Int] {
          override def append(a: Int, b: Int) = a + b
     }

     implicit val appendableString = new Appendable[String] {
          override def append(a: String, b: String) = a.concat(b)
     }
}

object AppendFactory{
     // note: Instead of taking an implicit conversion ev parameter, we give appendItems()
     // an actual implicit class instance.
     def appendItems_WithImplicitParam[A](a: A, b: A)(implicit ev: Appendable[A]) = ev.append(a, b)

     //note: this means method is parametrized with A and requires there must be an implicit
     // value Appendable[A] within scope. No longer have 'ev' so we need 'implicitly' keyword. 
     def appendItems_WithContextBound[A: Appendable](a: A, b: A) =
          implicitly[Appendable[A]].append(a, b)
}



object Main extends App {
     import Appendable._
     import AppendFactory._

     assert(appendItems_WithImplicitParam(2, 3) == 5)
     assert(appendItems_WithImplicitParam("2", "3") == "23")

     assert(appendItems_WithContextBound(2, 3) == 5)
     assert(appendItems_WithContextBound("2", "3") == "23")
}