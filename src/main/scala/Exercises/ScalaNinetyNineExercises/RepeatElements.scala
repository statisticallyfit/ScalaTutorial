package Exercises.ScalaNinetyNineExercises

import org.scalatest.Assertions._

/**
  *
  */
object RepeatElements {

     def repeat[T](list: List[T]): List[T] = list match {
          case Nil => Nil
          case h :: tail => h :: h :: repeat(tail)
     }

     def main(args: Array[String]) {
          assert(repeat(List(1,2,3)) === List(1, 1, 2, 2, 3, 3))
          assert(repeat(List(1)) === List(1,1))
          assert(repeat(List()) === List())


          assert(alternate(List(1,2,3,4,5,6)) === List(2,1,4,3,6,5))
          assert(alternate(List(1,2,3,4,5)) === List(2,1,4,3,5))
          assert(alternate(List(1)) === List(1))
          assert(alternate(List(1,2)) === List(2,1))
          assert(alternate(List()) === List())

          assert(everySecondElement(List(1,2,3,4,5,6)) === List(2,4,6))
          assert(everySecondElement(List(1,2,3,4,5)) === List(2,4))
          assert(everySecondElement(List(1)) === List())
          assert(everySecondElement(List(1,2)) === List(2))
          assert(everySecondElement(List()) === List())
     }


     def alternate[T](list: List[T]): List[T] = list match {
          case Nil => Nil
          case h :: Nil => Nil
          case a :: b :: Nil => b :: a :: Nil
          case a :: b :: tail => b :: a :: alternate(tail)
     }

     def everySecondElement[T](list: List[T]): List[T] = list match {
          case Nil => Nil
          case h :: Nil => Nil
          case a :: b :: Nil => b :: Nil
          case a :: b :: tail => b :: everySecondElement(tail)
     }

}
