package Books.AtomicScala.implicits

/**
  *
  */
object Quoting1 {

     /**
       * We are getting the methods through the implicit class
       */
     object Quoting {
          implicit class AnyName(s:String) {
               def singleQuote = s"'$s'"
               def doubleQuote = s""""$s""""
          }
     }

     def main(args: Array[String]) {

          import Quoting._

          assert("Hi".singleQuote == "'Hi'")
          assert("Hi".doubleQuote == "\"Hi\"")
     }
}
