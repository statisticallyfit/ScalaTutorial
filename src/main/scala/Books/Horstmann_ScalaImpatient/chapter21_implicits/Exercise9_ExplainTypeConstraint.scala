package Books.Horstmann_ScalaImpatient.chapter21_implicits

/**
  * source
  * https://github.com/BasileDuPlessis/scala-for-the-impatient/blob/master/src/main/scala/com/basile/scala/ch21/Ex09.scala
  */
object Exercise9_ExplainTypeConstraint {


     class Op[A](value:A){
          def concatenate(c:String)(implicit ev: A =:= String):String = value + c
          def multiply(n:Int)(implicit ev: A =:= Int):Int = value * n
     }

     def main(args: Array[String]) {
          assert(new Op("Hello").concatenate(" world") == "Hello world")
          assert(new Op(10).multiply(33) == 330)
     }
}
