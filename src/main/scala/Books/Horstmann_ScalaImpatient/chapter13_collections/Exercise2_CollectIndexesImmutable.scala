package Books.Horstmann_ScalaImpatient.chapter13_collections


object Exercise2_CollectIndexesImmutable {

     def collectIndices(input: String): Map[Char, Set[Int]] ={

          var result = Map[Char, Set[Int]]()
          for((key, newIndex) <- input.zipWithIndex){
               var currIndexes: Set[Int] = result.getOrElse(key.toChar, Set[Int]())
               currIndexes += newIndex
               result += (key -> currIndexes)
          }
          result
     }

     def collectIndicesFaster(input: String): Map[Char, Set[Int]] ={

          input.zipWithIndex.groupBy(_._1).map(pair => (pair._1, pair._2.map(_._2).toSet))
     }

     def main(args: Array[String]) {
          Console.println(collectIndices("Mississippi"))
          Console.println(collectIndicesFaster("Mississippi"))
     }
}
