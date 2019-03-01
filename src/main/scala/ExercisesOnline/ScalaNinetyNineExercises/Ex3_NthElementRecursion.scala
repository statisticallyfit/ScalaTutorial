package ExercisesOnline.ScalaNinetyNineExercises

/**
  *
  */
import org.scalatest.Assertions._

object Ex3_NthElementRecursion {


     //going by index, so count zero (means actually the (n+1)th element
     def nthElement[A](n: Int, list: List[A]): Option[A] = {

          def innerNth(pos: Int, currentList: List[A]): Option[A] = {
               currentList match {
                    case Nil => None
                    case _ if pos == n => {
                         println(s"pos = $pos, n = $n")
                         Some(currentList.head)
                    }
                    case _ :: tail => innerNth(pos + 1, tail)
               }
          }

          if(n < 0) None
          else innerNth(0, list)
     }



     def nthElemSolution[A](n: Int, list: List[A]): Option[A] = {
          (n, list) match {
               case (0, h :: _) => Some(h)
               case (n, _ :: tail) => nthElemSolution(n - 1, tail)
               case (_, Nil) => None
          }
     }


     def main(args: Array[String]) {
          assert(nthElement(2, List(1,4,5,6,7,8)) === Some(5), "Test 1")
          assert(nthElement(0, List(5)) === Some(5), "test 2")
          assert(nthElement(1, List(5)) === None, "Test 3")
          assert(nthElement(1, List()) === None, "Test 4")
          assert(nthElement(0, List()) === None, "Test 5")

          assert(nthElemSolution(2, List(1,4,5,6,7,8)) === Some(5), "Test 6")
          assert(nthElemSolution(0, List(5)) === Some(5), "test 7")
          assert(nthElemSolution(1, List(5)) === None, "Test 8")
          assert(nthElemSolution(1, List()) === None, "Test 9")
          assert(nthElemSolution(0, List()) === None, "Test 10")
     }
}
