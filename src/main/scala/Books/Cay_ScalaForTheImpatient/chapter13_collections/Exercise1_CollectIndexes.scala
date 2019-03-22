package Books.Cay_ScalaForTheImpatient.chapter13_collections

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  *
  */
object Exercise1_CollectIndexes {

     def collectIndexes(input: String): Map[Char, Set[Int]] ={
          /*val result = ArrayBuffer[(Char, List[Int])]()
          input.zipWithIndex.foreach((ci: (Char, Int)) =>
               result(ci._1) = (ci._1, result(ci._1)._2 :+ ci._2))

          result.toMap*/

          val result = mutable.Map[Char, mutable.Set[Int]]()
          for((key, newIndex) <- input.zipWithIndex){
               val currIndexes = result.getOrElse(key.toChar, mutable.Set[Int]())
               currIndexes += newIndex
               result(key) = currIndexes
          }
          result.map((kv:(Char,mutable.Set[Int])) => kv._1 -> kv._2.toSet).toMap

          //help why can't do this?
          /*input.zipWithIndex.foreach((kv:(Char, mutable.Set[Int])) => result(kv._1) = result.getOrElse(kv._1.toChar,
               mutable.Set[Int]()) + kv._2)*/
     }

     def main(args: Array[String]) {
          Console.println(collectIndexes("Mississippi"))
     }
}
