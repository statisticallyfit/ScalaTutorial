package Horstmann_ScalaImpatient.chapter2_functions

/**
  *
  */
object Exercise3_TranslateJavaLoop {

     def incrementLoop(start:Int, finish:Int): IndexedSeq[Int] = {
          for (i <- start to finish by 1)
               yield i
     }

     def decrementLoop(start:Int, finish:Int): IndexedSeq[Int] = {
          for (i <- start to finish by -1)
               yield i
     }

     def main(args: Array[String]) {
          println(incrementLoop(1, 10))
          println(decrementLoop(10, 1))
     }
}
