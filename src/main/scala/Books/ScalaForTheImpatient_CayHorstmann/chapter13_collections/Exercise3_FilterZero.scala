package Books.ScalaForTheImpatient_CayHorstmann.chapter13_collections



object Exercise3_FilterZero {

     def filterZero(list: List[Int]): List[Int] = list match {
          case Nil => List()
          case (x :: xs) => if(x == 0) filterZero(xs) else x :: filterZero(xs)
     }

     def main(args: Array[String]) {
          Console.println(filterZero(List(1, 2, 3, 4, 0, 5, 6, 0, 8, 0, 0, 3, 4, 0, 1, -1, 0)))
     }
}
