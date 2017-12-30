package Books.Eckel_AtomicScala

/**
  * We don't need the outside object to store the implicit class
  * so we can just use the value type which doesn't make an object
  * but just makes the call. To turn AnyName to value type you
  * must inherit from AnyVal and the class argument must be 'val'
  */
package object Quoting2 {

     implicit class AnyName(val s:String) extends AnyVal {
          def singleQuote = s"'$s'"
          def doubleQuote = s""""$s""""
     }
}

object Quoting2Test {
     import Quoting2._

     def main(args: Array[String]) {
          assert("Hi".singleQuote == "'Hi'")
          assert("Hi".doubleQuote == "\"Hi\"")
          Console.println("Hi".singleQuote)
          Console.println("Hi".doubleQuote)
     }
}
