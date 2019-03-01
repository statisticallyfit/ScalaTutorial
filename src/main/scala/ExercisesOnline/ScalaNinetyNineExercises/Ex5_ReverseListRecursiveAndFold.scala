package ExercisesOnline.ScalaNinetyNineExercises

/**
  *
  */
import org.scalatest.Assertions._

object Ex5_ReverseListRecursiveAndFold {

     def reverseRecursive[A](ls: List[A]): List[A] = ls match {
          case Nil       => Nil
          case h :: tail => reverseRecursive(tail) ::: List(h) //note triple colon
     }
     /*def reverseRecursive[A](list: List[A]): List[A] = list match {
          case Nil => Nil
          case h :: tail => reverseRecursive(tail) :: List(h)
     }*/

     def reverseTailRecursive[A](list: List[A]): List[A] ={

          def rev(accList: List[A], currList: List[A]): List[A] ={
               currList match {
                    case Nil => accList
                    case h :: tail => rev(h :: accList, tail)
               }
          }

          rev(Nil, list)
     }


     def reverseFoldRight[A](list: List[A]): List[A] ={
          val seed: List[A] = List[A]()

          list.foldRight(seed)((xElem, accList) => accList ::: List(xElem))
     }


     def reverseFoldLeft[A](list: List[A]): List[A] ={
          val seed: List[A] = List[A]()

          list.foldLeft(seed)((accList, xElem) => xElem :: accList)
     }


     def main(args: Array[String]) {
          assert(reverseRecursive(List()) === List(), "Test 1")
          assert(reverseRecursive(List(1)) === List(1), "Test 2")
          assert(reverseRecursive(List(1,2)) === List(2,1), "Test 3")
          assert(reverseRecursive(List(1,2,3)) === List(1,2,3).reverse, "Test 4")
          assert(reverseRecursive(List(1,2,3,4)) === List(1,2,3,4).reverse, "Test 5")

          assert(reverseTailRecursive(List()) === List(), "Test 6")
          assert(reverseTailRecursive(List(1)) === List(1), "Test 7")
          assert(reverseTailRecursive(List(1,2)) === List(2,1), "Test 8")
          assert(reverseTailRecursive(List(1,2,3)) === List(1,2,3).reverse, "Test 9")
          assert(reverseTailRecursive(List(1,2,3,4)) === List(1,2,3,4).reverse, "Test 10")


          assert(reverseFoldRight(List()) === List(), "Test 11")
          assert(reverseFoldRight(List(1)) === List(1), "Test 12")
          assert(reverseFoldRight(List(1,2)) === List(2,1), "Test 13")
          assert(reverseFoldRight(List(1,2,3)) === List(1,2,3).reverse, "Test 14")
          assert(reverseFoldRight(List(1,2,3,4)) === List(1,2,3,4).reverse, "Test 15")


          assert(reverseFoldLeft(List()) === List(), "Test 16")
          assert(reverseFoldLeft(List(1)) === List(1), "Test 17")
          assert(reverseFoldLeft(List(1,2)) === List(2,1), "Test 18")
          assert(reverseFoldLeft(List(1,2,3)) === List(1,2,3).reverse, "Test 19")
          assert(reverseFoldLeft(List(1,2,3,4)) === List(1,2,3,4).reverse, "Test 20")
     }
}
