package Books.Horstmann_ScalaImpatient.chapter13_collections

/**
  *
  */
object Exercise5_MkString {

     def mkStringReduce[T](input: Seq[T], sep: String): String ={
          input.map(_.toString).reduceLeft((acc, y) => acc + sep + y)
     }

     def mkStringFold[T](input: Seq[T], sep: String): String ={
          input.tail.foldLeft(input.head.toString)((acc,y) => acc + sep + y.toString)
     }

     def main(args: Array[String]) {

          Console.println(mkStringFold((0 to 10).toSeq, ", "))
          Console.println(mkStringReduce((0 to 10).toSeq, ", "))
     }
}
