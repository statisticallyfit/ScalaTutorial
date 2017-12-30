package Books.Eckel_AtomicScala.Methods

/**
  * Create a method manyTimesString that takes a String and an Int
  * as arguments and returns the String duplicated that many times.
  */

object DuplicateString {

     def manyTimesString(s: String, repNum: Int) : String = {
          s * repNum
     }

     def main(args: Array[String]) {

          val m1 = manyTimesString("abc", 3)
          assert("abcabcabc" == m1, "see Test 1")

          val m2 = manyTimesString("grape", 7)
          assert("grapegrapegrapegrapegrapegrapegrape" == m2, "see test 2")
     }
}