package other.HackerRank.WeekOfCode23


import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn
/**
  * Print if gears will turn (hint: if odd num of gears, they will not
  * turn but if even they will turn. First entry input is num of queries)
  */
object GearsOfWar {


     def getQueries(numQueries: Int): List[Int] ={
          val queries: ArrayBuffer[Int] = ArrayBuffer()
          (1 to numQueries).foreach(
               //note don't use readInt cause it won't skip to next line
               i => queries += StdIn.readLine.toInt
          )
          queries.toList
     }

     def willTurn(numGears: Int): Boolean = if(numGears == 0) false else numGears % 2 == 0
}

object Solution {

     def main(args: Array[String]) {

          //entering number of queries
          val numQueries = StdIn.readLine("Number of queries?: ").toInt

          val gearNumList: List[Int] = GearsOfWar.getQueries(numQueries)

          gearNumList.map(g => GearsOfWar.willTurn(g)).foreach(b => if(b) println("Yes") else println("No"))
     }
}
