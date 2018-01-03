package Books.ScalaForTheImpatient_CayHorstmann.chapter14_patternMatchingWithCaseClasses

/**
  *
  */
object Exercise5_Leaf {

     def leafSum(list: List[Any]): Int ={
          list.map(elem => elem match {
               case Nil => 0
               case n:Int => n
               case xs:List[Any] => leafSum(xs)
          }).sum
     }

     def main(args: Array[String]) {
          val list = List(1, List(List(2), 5), List(3), List(4, List(List(List(9)), 8), 7))
          assert(leafSum(list) == 39)
          assert(leafSum(List(2)) == 2)
     }
}
