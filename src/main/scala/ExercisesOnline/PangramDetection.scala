package ExercisesOnline

import scala.collection._
import scala.io.StdIn
/**
  *
  */
object PangramDetection {

     /*note param input contains every letter of alphabet at least once */
     def isPangramLessEfficient(input: String): Boolean = {

          val NUM_LETTERS: Int = 26
          val vs: List[Boolean] = List.fill(NUM_LETTERS)(false)
          val ks: List[Char] = "abcdefghijklmnopqrstuvwxyz".toCharArray.toList
          val tracker: mutable.Map[Char, Boolean] = scala.collection.mutable.Map() ++
               ks.zip(vs).collect { case (k, v) => k -> v}.toMap

          input.toLowerCase.foreach(c => if(c.isLetter) { tracker(c) = true })

          tracker.values.count(v => v) == NUM_LETTERS
     }

     def isPangram(input: String): Boolean = {
          val alphabet: String = "abcdefghijklmnopqrstuvwxyz"
          alphabet == input.toLowerCase.distinct.filter(_.isLetter).sorted
     }


     def main(args: Array[String]) {


          val input: String = StdIn.readLine
          if(isPangram(input)) println("pangram")
          else println("not pangram")

          assert(isPangram("The quick brown fox jumps over the lazy dog"), "Test 0")
          assert(isPangram("We promptly judged antique ivory buckles for the next prize"), "Test 1")
          assert(!isPangram("We promptly judged antique ivory buckles for the prize"), "Test 2")
          assert(isPangramLessEfficient("We promptly judged antique ivory buckles for the next prize"),
               "Test 3")
          assert(!isPangramLessEfficient("We promptly judged antique ivory buckles for the prize"),
               "Test 4")
     }

}
