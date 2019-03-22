package Books.Cay_ScalaForTheImpatient.chapter13_collections

/**
  *
  */
object Exercise4_GetValues {

     def existIn(masterKeys: Seq[String], theMap: Map[String, Int]): Seq[Int] ={
          masterKeys.map(k => theMap.get(k)).flatten
          //or just say
          //masterKeys.flatMap(k => theMap.get(k))
     }

     def main(args: Array[String]) {
          Console.println(existIn(Array("Tom", "Fred", "Harry"),
               Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)))
     }
}
