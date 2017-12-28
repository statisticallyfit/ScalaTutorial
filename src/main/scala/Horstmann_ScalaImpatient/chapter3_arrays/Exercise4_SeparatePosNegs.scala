package Horstmann_ScalaImpatient.chapter3_arrays

/**
  *
  */
object Exercise4_SeparatePosNegs {

     def main(args: Array[String]) {

          val array = Array(-7, 4, 3, 1, 0, -9, -4, -6, 8, -22)
          var pos = Array[Int]()
          var neg = Array[Int]()

          // solution 1
          pos = array.collect {
               case x:Int if(x > 0) => x
          }
          neg = array.collect {
               case x:Int if(x <= 0) => x
          }
          println("solution 1: ")
          println("positives: " + pos.mkString(", "))
          println("negatives: " + neg.mkString(", "))


          // solution 2
          pos = array.filter(_ > 0)
          neg = array.filter(_ <= 0)
          println("\nsolution 2: ")
          println("positives: " + pos.mkString(", "))
          println("negatives: " + neg.mkString(", "))


          // solution 3
          val result = array.partition(_ > 0)
          pos = result._1
          neg = result._2
          println("\nsolution 3: ")
          println("positives: " + pos.mkString(", "))
          println("negatives: " + neg.mkString(", "))
     }
}
