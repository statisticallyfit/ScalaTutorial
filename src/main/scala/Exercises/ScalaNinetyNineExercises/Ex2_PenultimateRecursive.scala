package Exercises.ScalaNinetyNineExercises

import org.scalatest.Assertions._

/**
  *
  */
object Ex2_PenultimateRecursive {
     def penultimate[A](list: List[A]): Option[A] = list match {

          case a :: b :: Nil => Some(a)
          case _ :: more => penultimate(more)
          case Nil => None

     }



     def main(args: Array[String]) {
          assert(penultimate(List(1,2,3,4)) === Some(3))
          assert(penultimate(List()) === None)

     }
}
