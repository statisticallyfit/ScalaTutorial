package HackerRank

import scala.collection.mutable
import scala.io.StdIn

/**
  *
  */
object SubstringSearching {

     def getNumCases: Int = StdIn.readInt() //Source.stdin.getLines()

     def getCases = {
          val numCases = getNumCases
          val mapCases = mutable.Map[String, String]()
          for(i <- 1 to numCases) {
               println("Text " + i)
               val text = StdIn.readLine() //Source.stdin.getLines().toString
               println("Pattern " + i)
               val pattern = StdIn.readLine()
               mapCases += (text -> pattern)
          }
          mapCases
     }

     def patternMatcher(cases: mutable.Map[String, String]) = {
          cases.foreach {
               case (text, pattern) if text contains pattern => println("YES")
               case (text, pattern) if !text.contains(pattern) => println("NO")
          }
     }


     // todo: not done prints in wrong order!
     def main(args: Array[String]) {
          println("How many tests: ")
          patternMatcher(getCases)
     }
}
