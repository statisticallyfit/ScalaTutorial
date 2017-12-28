package Horstmann_ScalaImpatient.chapter3_arrays

/**
  *
  */
object Exercise7_NoDuplicates {

     def main(args: Array[String]): Unit ={

          val duplicates = Array(1, 1, 3, 4, 5, 3, 3, 8, 9, 1, 5, 5, 8)
          println(duplicates.distinct.mkString(", "))
     }
}
