package Horstmann_ScalaImpatient.chapter14_patternMatchingWithCaseClasses

/**
  *
  */
object Exercise9_SumOptions {


     def sumOptions(options: List[Option[Int]]): Int ={
          options.map(opt => opt.getOrElse(0)).sum
     }

     def main(args: Array[String]) {
          assert(sumOptions(List(Some(1), None, Some(20), Some(-13), None, Some(5))) == 13)
     }
}
