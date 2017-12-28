package Eckel_AtomicScala.classes.NamedAndDefaultArgs

/**
  *
  */


object Exercise1_2_SimpleTime {

     class SimpleTime(val hours: Int, val minutes: Int=0)


     def main(args: Array[String]) {
          val t1 = new SimpleTime(hours=5, minutes=30)
          assert(t1.hours == 5)
          assert(t1.minutes == 30)

          val t2 = new SimpleTime(hours=10)
          assert(t2.hours == 10)
          assert(t2.minutes == 0)
     }
}