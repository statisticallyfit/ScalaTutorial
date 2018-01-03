package Books.ScalaForTheImpatient_CayHorstmann.chapter8_inheritance

/**
  *
  */
object Exercise8_SecretAgent {


     class Person (val name: String) {
          override def toString = getClass.getName + "[name = " +
               name + "]"
     }

     class SecretAgent (codeName: String) extends Person (codeName) {
          override val name = "secret"
          override val toString = "secret"
     }


     def main(args: Array[String]) {

          val p = new Person("Sarah")
          println(p)
          val s = new SecretAgent("Cameron The Cameleon")
          println(s)
     }
}
