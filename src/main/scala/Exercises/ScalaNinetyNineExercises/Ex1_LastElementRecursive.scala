package Exercises.ScalaNinetyNineExercises

/**
  *
  */

import org.scalatest.Assertions._


object Ex1_LastElementRecursive {
     def last[A](list: List[A]): Option[A] = list match {
          case theLast :: Nil => Some(theLast)
          case _ :: more => last(more)
          case _ => None
     }

     def main(args: Array[String]) {

          assert(last(List(1, 12, 3)) === Some(3))
          assert(last(List(1)) === Some(1))
          assert(last(List()) === None)
     }
}
