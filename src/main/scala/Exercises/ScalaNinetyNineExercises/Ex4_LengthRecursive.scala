package Exercises.ScalaNinetyNineExercises

/**
  *
  */



object Ex4_LengthRecursive {


     def lengthSimpleRecursive[A](list: List[A]): Int = {
          list match {
               case Nil => 0
               case _ :: tail => 1 + lengthSimpleRecursive(tail)
          }
     }


     def lengthTailRecursive[A](list: List[A]): Int = {

          def innerLength(accLen: Int, currList: List[A]): Int = {
               currList match {
                    case Nil => accLen
                    case _ :: tail => innerLength(accLen + 1, tail)
               }
          }

          innerLength(0, list)
     }

     def main(args: Array[String]) {
          assert(lengthSimpleRecursive(List(1,2,3)) == 3, "Test 1") //odd length
          assert(lengthSimpleRecursive(List(1)) == 1, "Test 2")
          assert(lengthSimpleRecursive(List()) == 0, "Test 3")
          assert(lengthSimpleRecursive(List(1,2,3,4)) == 4, "Test 4") //even length


          assert(lengthTailRecursive(List(1,2,3)) == 3, "Test 1") //odd length
          assert(lengthTailRecursive(List(1)) == 1, "Test 2")
          assert(lengthTailRecursive(List()) == 0, "Test 3")
          assert(lengthTailRecursive(List(1,2,3,4)) == 4, "Test 4") //even length
     }
}
