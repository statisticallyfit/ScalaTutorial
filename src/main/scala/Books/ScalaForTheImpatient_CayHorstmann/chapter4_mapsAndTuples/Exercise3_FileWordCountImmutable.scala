package Books.ScalaForTheImpatient_CayHorstmann.chapter4_mapsAndTuples

import scala.collection.immutable
import scala.io.Source

/**
  *
  */
object Exercise3_FileWordCountImmutable {

     def readFile(fileName: String) ={
          // setting the path
          Source.fromFile(
               "/datascience/projects/statisticallyfit/github/" +
                    "learningprogramming/Scala/ScalaTutorial/src/" +
                    "main/scala/bookScalaImpatient/chapter4_maps_and_tuples/" +
                    fileName, "UTF-8")
               .getLines().mkString.split(" ")
          // mkString(" ")  will look ugly if the lines themselves start with space
     }


     def linkCountToWords(content: Array[String]) = {
          var freqMap = immutable.Map[String, Int]()

          // done with help from hempalex github source
          // token is just a word in the list of words that content holds
          content foreach { token =>
               // the part (token -> ...) creates a tuple made of the key and value
               // token = key
               // count = value
               freqMap = freqMap + (token -> (freqMap.getOrElse(token, 0) + 1))
          }
          freqMap
     }

     def main(args: Array[String]): Unit = {

          val tokens = readFile("athertonFileReading.txt")
          val freqs = linkCountToWords(tokens)

          freqs.foreach(println)
     }
}
