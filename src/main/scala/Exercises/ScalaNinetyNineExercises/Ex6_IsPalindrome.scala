package Exercises.ScalaNinetyNineExercises

/**
  *
  */
import org.scalatest.Assertions._

object Ex6_IsPalindrome {


     def isPalindrome[A](list: List[A]): Boolean = {
          list match {
               case Nil => true //means either empty list or even length
               case List(_) => true // list of a single element is a palindrome
               case h :: tail => (h == tail.last) && isPalindrome(tail.init)
          }
     }

     def main(args: Array[String]) {
          assert(isPalindrome(List("a")), "Test 0")
          assert(isPalindrome(List()), "Test 01")
          assert(isPalindrome("racecar".split("").toList), "Test 1")
          assert(!isPalindrome("mustard".split("").toList), "Test 2")
     }
}
