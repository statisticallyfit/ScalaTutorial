package Books.Cay_ScalaForTheImpatient.chapter4_mapsAndTuples

import scala.collection.mutable
import scala.io.Source

/**
  *
  */
object Exercise2_FileWordCount {


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
          val freqMap = mutable.Map[String, Int]()

          content foreach { token =>
               if (freqMap.contains(token))
                    freqMap(token) += 1
               else
                    freqMap(token) = 1
          }

          freqMap
     }

     def main(args: Array[String]): Unit = {

          val tokens = readFile("athertonFileReading.txt")
          val freqs = linkCountToWords(tokens)
          freqs.foreach(println)
     }
}
