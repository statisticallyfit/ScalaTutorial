package Exercises

import scala.language.implicitConversions


object ReverseElements {

     object DefReverse {
          class ReverseWrapper[T](val items: List[T]){
               def myReverser: List[T] = {

                    def rev(its: List[T]): List[T] = its match {
                         case Nil => its
                         case x :: xs => rev(xs) :+ x
                    }
                    rev(items)
               }
          }

          implicit def reverserMaker[T](items: List[T]): ReverseWrapper[T] = new ReverseWrapper[T](items)
     }



     def main(args: Array[String]) {
          import DefReverse._

          val ints = (0 to 10).toList
          val strings = "firetruck".toList

          Console.println(ints.myReverser)
          Console.println(strings.myReverser)
          Console.println((0 to 2000).toList.myReverser)
     }
}
