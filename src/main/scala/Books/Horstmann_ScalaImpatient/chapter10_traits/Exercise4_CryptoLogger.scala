package Books.Horstmann_ScalaImpatient.chapter10_traits


object Exercise4_CryptoLogger extends App {


     trait Logger {
          def log(msg: String) { }
     }

     trait ConsoleLogger extends Logger {
          override def log(msg: String) = Console.println(msg)
     }

     trait CryptoLogger extends Logger with Shift {

          val shift: Int // note the value gets here by trait mixins.

          // note need to write abstract when using super and super.log is abstract
          /*abstract */override def log(input: String) = {
               super.log(input.map(shiftWrap).mkString)
          }
     }

     trait Shift {
          val shift: Int // note the value gets here by trait mixins.

          def shiftWrap(c: Char): Char ={
               if(c.isLower) ((c - 'a' + shift) % 26 + 'a').toChar
               else if(c.isUpper) ((c - 'A' + shift) % 26 + 'A').toChar
               else if(c.isDigit) ((c - '0' + shift) % 26 + '0').toChar
               else c
          }

     }


     class Encryption extends Logger {
          val shift: Int = 3
          def encrypt(msg: String) = log(msg)
     }

     // help todo: is there another way to override default shift amount? other than by early
     // definition method?
     /*class AnotherEncryption(shift: Int = 3) extends Logger {
          val shft = shift
          def encrypt(msg: String) = log(msg)
     }*/


     val console = new Encryption with ConsoleLogger
     console.encrypt("meet at dawn")


     val cipher = new Encryption with ConsoleLogger with CryptoLogger
     println(cipher.shift)
     cipher.encrypt("meet at dawn")


     val cipherOverride = new {
          override val shift = 10
     } with Encryption with ConsoleLogger with CryptoLogger
     cipherOverride.encrypt("meet at dawn")

     //val cipherAnother = new AnotherEncryption(10) with ConsoleLogger with CryptoLogger
}