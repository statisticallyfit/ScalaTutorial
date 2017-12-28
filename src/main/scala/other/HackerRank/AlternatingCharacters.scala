package HackerRank

import scala.io.StdIn
import scala.collection.mutable.ArrayBuffer

/**
  *
  */
object AlternatingCharacters {


     def minDeletionsRequired(input: String): Int = {

          var index: Int = 0
          var numDeletesReq: Int = 0
          val len = input.length

          while(index < len - 1) {
               if(input(index) == input(index + 1)){
                    numDeletesReq += 1
               }
               index += 1
          }
          numDeletesReq
     }

     def acceptInputs(numInputs: Int): List[String] = {
          var numAccepted: Int = 0
          val inputs: ArrayBuffer[String] = ArrayBuffer()

          while(numAccepted < numInputs){
               inputs += StdIn.readLine
               numAccepted += 1
          }
          inputs.toList
     }


     def decideMinDeletions(inputs: List[String]): Unit ={
          inputs.map(minDeletionsRequired).foreach(println)
     }


     def main(args: Array[String]) {
          val numInputs: Int = StdIn.readLine.toInt
          val inputs: List[String] = acceptInputs(numInputs)
          decideMinDeletions(inputs)
     }
}
