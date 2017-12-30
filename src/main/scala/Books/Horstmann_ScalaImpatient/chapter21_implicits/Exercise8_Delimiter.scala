package Books.Horstmann_ScalaImpatient.chapter21_implicits


/**
  *
  */
object Exercise8_Delimiter {

     object Delimiter {

          case class Delimiters(left:String, right:String)

          def quote(what:String)(implicit delims:Delimiters):String =
               delims.left + what + delims.right

          def quoteAnother(what:String):String
               = implicitly[Delimiters].left + what + implicitly[Delimiters].right

          implicit val frenchDelimiters: Delimiters = Delimiters("<<", ">>")
          //implicit val englishDelimiters = Delimiters("'", "'")
     }


     object Smaller {

          //equivalent to: def smaller[T <% Ordered[T]](a:T, b:T): T = ....
          def smaller[T](a:T, b:T)(implicit ord: T => Ordered[T]): T = if(a < b) a else b
     }

     def main(args: Array[String]) {
          {
               import Delimiter._

               Console.println(implicitly[Delimiters])
               Console.println(quote("Bon jour!"))
               Console.println(quoteAnother("Bon jour!"))
          }

          //------------------------------------------------
          {
               import Smaller._

               //works because there is an implicit conversion from String to Ordered[String]
               Console.println(smaller("penguin", "jaguar"))
               //note cannot call implicitly[..] because there is no implicit value declared... like
               // frenchConversions
          }
     }
}
