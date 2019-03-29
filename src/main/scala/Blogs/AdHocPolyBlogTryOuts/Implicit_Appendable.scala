package Blogs.AdHocPolyBlogTryOuts

// note: Source: https://medium.com/@sinisalouc/ad-hoc-polymorphism-and-type-classes-442ae22e5342



import scala.language.implicitConversions



object Implicit_Appendable {


     // just here to provide an interface for appending.
     trait Appendable[A] {
          def append(a: A): A
     }
     // defining different types of appending.
     class AppendableInt(i: Int) extends Appendable[Int] {
          override def append(a: Int) = i + a
     }

     class AppendableString(s: String) extends Appendable[String] {
          override def append(a: String) = s.concat(a)
     }

     object AppendFactory {
          // implicit appending definitions to serve as evidence in
          // our culminating method appendItems().
          implicit def toAppendable(i: Int): Appendable[Int] = new AppendableInt(i)
          implicit def toAppendable(s: String): Appendable[String] = new AppendableString(s)

          // here we must provide compiler with implicit conversion evidence, defined above using
          // classes defined for int and string appending.
          def appendItems[A](a: A, b: A)(implicit ev: A => Appendable[A]) = a append b
     }



     def main(args: Array[String]) {

          import AppendFactory._

          assert(AppendFactory.appendItems(2, 3) == 5)
          assert(AppendFactory.appendItems("2", "3") == "23")
     }
}