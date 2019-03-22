package Exercises

import org.scalatest.Assertions._

/**
  *
  */
object SecondElement {


     def everySecondElement[T](list: List[T]): List[T] = list match {
          case Nil => Nil
          case h :: Nil => Nil
          case a :: b :: Nil => b :: Nil
          case a :: b :: tail => b :: everySecondElement(tail)
     }

     def main(args: Array[String]) {
          assert(everySecondElement(List(1,2,3,4,5,6)) === List(2,4,6))
          assert(everySecondElement(List(1,2,3,4,5)) === List(2,4))
          assert(everySecondElement(List(1)) === List())
          assert(everySecondElement(List(1,2)) === List(2))
          assert(everySecondElement(List()) === List())
     }

}
