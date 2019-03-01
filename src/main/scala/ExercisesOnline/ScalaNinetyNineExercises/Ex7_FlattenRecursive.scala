/*
package ExercisesOnline.ScalaNinetyNineExercises

import cats._
import cats.implicits._
import org.scalatest.Assertions._
/**
  *
  */
object Ex7_FlattenRecursive {


     //help sas implicits type mismatch
     /*def flattenWithFoldLeft[A](list: List[A])(implicit m: Monoid[A]): A = {
          list.foldLeft(m.empty)((acc, xElem) => m.combine(acc, xElem))
     }*/

     def flattenRecursive[A](list: List[A]): A = {
          def inner()
     }

     def main(args: Array[String]) {
          val listLists: List[List[Int]] = List(List(1,2,3), List(9,4,3,3), List(5,5,9))
          assert(flattenWithFoldLeft(listLists) === listLists.flatten)

          val listOfOptions: List[Option[Int]] = List(Some(3), None, Some(5))
          assert(flattenWithFoldLeft(listOfOptions) === listOfOptions.flatten)
     }
}
*/
