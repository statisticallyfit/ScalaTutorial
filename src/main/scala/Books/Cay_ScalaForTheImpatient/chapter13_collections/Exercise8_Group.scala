package Books.Cay_ScalaForTheImpatient.chapter13_collections

/**
  *
  */
object Exercise8_Group {

     def groupMaker(array: Seq[Int], colSize: Int): Array[Array[Int]] ={
          array.grouped(colSize).toArray.map(_.toArray)
     }

     def main(args: Array[String]) {
          val array = (0 to 10).toArray
          Console.println(groupMaker(array, 3).deep.mkString(" | "))
          Console.println(groupMaker(array, 4).deep.mkString(" | "))
          Console.println(groupMaker(array, 10).deep.mkString(" | "))
     }
}
