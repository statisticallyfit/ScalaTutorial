package Books.Horstmann_ScalaImpatient.chapter13_collections

/**
  *
  */
object Exercise6_ReverseFold {

     def main(args: Array[String]) {

          val list = List(1,2,3,4,5)

          val originalRight = list.foldRight(List[Int]())(_ :: _)
          val reverseRight = list.foldRight(List[Int]())((x,acc) => acc :+ x)
          Console.println("original foldRight: " + originalRight)
          Console.println("reverse foldRight: " + reverseRight)

          val originalLeft = list.foldLeft(List[Int]())(_ :+ _)
          val reverseLeft = list.foldLeft(List[Int]())((acc, y) => y +: acc)
          Console.println("original foldLeft: " + originalLeft)
          Console.println("reverse foldLeft: " + reverseLeft)
     }
}
