package Books.Horstmann_ScalaImpatient.chapter3_arrays

import scala.collection.mutable.ArrayBuffer

/**
  *
  */
object Exercise8_RemoveTailNegatives {

     def myExample1(aBuf: ArrayBuffer[Int]): Unit = {

          var indexNegatives = (for (i <- aBuf.indices if aBuf(i) < 0) yield i)
               .reverse
               .dropRight(1)
               .foreach(aBuf.remove(_))
     }

     def myExample2(aBuf: ArrayBuffer[Int]): ArrayBuffer[Int] = {

          val indexFirstNegative = aBuf.indexWhere(_ < 0)
          aBuf.takeWhile(_ > 0) +=
               aBuf(indexFirstNegative) ++=
               aBuf.takeRight(aBuf.length - indexFirstNegative - 1).filter(_ >= 0)
     }

     def myExample3(aBuf: ArrayBuffer[Int]): ArrayBuffer[Int] = {

          val indexOfFirstNeg = aBuf.indexWhere(_ < 0)
          val firstElemsAndFirstNeg = aBuf.dropRight(aBuf.length-1 - indexOfFirstNeg)
          val endElemsAsPos = aBuf.drop(firstElemsAndFirstNeg.length).filter(_ >= 0)
          firstElemsAndFirstNeg ++= endElemsAsPos
     }


     def verboseExample(buffer: ArrayBuffer[Int]): Unit ={
          var thisNumIsTheFirstNegative = true
          var len = buffer.length
          var i = 0
          while (i < len){
               if (buffer(i) >= 0)
                    i += 1 //continue searching
               else {
                    if (thisNumIsTheFirstNegative) {
                         thisNumIsTheFirstNegative = false
                         i += 1
                    } else {
                         buffer.remove(i)
                         len -= 1
                    }
               }
          }
     }


     def betterExample(aBuf: ArrayBuffer[Int]): Unit = {

          var first = true
          // array.indices = 0 until array.length
          val indexes = for (i <- aBuf.indices if first || aBuf(i) >= 0)
               yield {
                    if (aBuf(i) < 0)
                         first = false; i
               }
          // now remove elements back to where they belong and trim the end
          for (j <- indexes.indices){
               aBuf(j) = aBuf(indexes(j))
          }
          aBuf.trimEnd(aBuf.length - indexes.length) // trim amount remained after copying over the array
     }


     def main(args: Array[String]) {
          val arrayBuffer = ArrayBuffer(9, 5, 1, -8, 7, -5, -2, -1, 0, 5)

          println("The array is: " + arrayBuffer.mkString(", ") + "\n")

          var copy = arrayBuffer.clone()
          verboseExample(copy)
          println("Example verbose: " + copy.mkString(", "))

          copy = arrayBuffer.clone()
          betterExample(copy)
          println("Example better: " + copy.mkString(", "))

          copy = arrayBuffer.clone()
          myExample1(copy)
          println("Example 1: " + copy.mkString(", "))

          copy = arrayBuffer.clone()
          println("Example 2: " + myExample2(copy).mkString(", "))

          copy = arrayBuffer.clone()
          println("Example 3: " + myExample3(copy).mkString(", "))
     }
}
