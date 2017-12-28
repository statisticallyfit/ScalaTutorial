package Eckel_AtomicScala.collections.ListsAndRecursion

/**
  *
  */
object Exercise4_Freq {


     def calcFreq(list: Seq[String], elem: String, fromIndex: Int): Int ={
          def freq(list: Seq[String], elem: String, runningCount: Int): Int ={
               if(list.isEmpty) runningCount
               else freq(list.tail, elem, if(elem == list(0)) runningCount + 1 else runningCount)
          }
          freq(list.drop(fromIndex), elem, 0)
     }

     def main(args: Array[String]) {

          val animalList = List("cat", "dog", "goose", "giraffe", "cockatoo", "cat", "eagle", "cat",
               "cat", "elephant", "gazelle", "antelope", "tiger", "cat", "frog", "penguin", "snow leopard",
               "cat", "bear", "pig", "chicken", "ostrich", "hummingbird", "cat", "white bear", "wolf", "condor")

          Console.println(calcFreq(animalList, "cat", 0))
          assert(calcFreq(animalList, "cat", 0) == 7)
          Console.println(calcFreq(animalList, "cat", 9))
          assert(calcFreq(animalList, "cat", 9) == 3)
     }
}
