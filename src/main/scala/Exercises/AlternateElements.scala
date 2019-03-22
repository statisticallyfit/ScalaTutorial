package Exercises

import org.scalatest.Assertions._

/**
  *
  */
object AlternateElements {


     def alternate[T](list: List[T]): List[T] = list match {
          case Nil => Nil
          case h :: Nil => h :: Nil
          case a :: b :: Nil => b :: a :: Nil
          case a :: b :: tail => b :: a :: alternate(tail)
     }

     def main(args: Array[String]) {

          assert(alternate(List(1,2,3,4,5,6)) === List(2,1,4,3,6,5), "Test 1")
          assert(alternate(List(1,2,3,4,5)) === List(2,1,4,3,5), "Test 2")
          assert(alternate(List(1)) === List(1), "Test 3")
          assert(alternate(List(1,2)) === List(2,1), "Test 4")
          assert(alternate(List()) === List(), "Test 5")
     }

}
