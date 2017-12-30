package Books.Eckel_AtomicScala.functions.FunctionsAsObjects

/**
  *
  */
object Exercise9_Pluralize {


     def main(args: Array[String]) {

          var holder = ""
          val words = Vector("word", "cat", "animal", "ocean")
          words.foreach(w => holder += (w + "s "))
          holder = holder.trim
          println(holder)
          assert(holder == "words cats animals oceans")
     }
}
