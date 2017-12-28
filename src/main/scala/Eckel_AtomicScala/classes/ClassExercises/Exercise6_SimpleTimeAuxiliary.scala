package Eckel_AtomicScala.classes.ClassExercises

import scala.Predef._
/**
  *
  */
object Exercise6_SimpleTimeAuxiliary {

     class SimpleTimeAux(var hours: Int, var minutes: Int){

          def this(hours: Int) = this(hours, 0)
          //def this(mins: Int) = this(0, mins)
          // todo help is this why defaulting both with
          // auxiliary is problematic?

          // note only supports military time :from
          // 00:00 on day 1 to 23.59 on day 1 (24:00 on day 1 is 0:00 on day 2)
          // note if given hour 24 or greater, then makes it 0 and keeps minutes then does subtraction
          // Assume we keep the same day
          // example: 24:23 subtract 17:12 equals ==> 4:49
          def subtract(that: SimpleTimeAux): SimpleTimeAux = {

               if (this.hours >= 24) {
                    this.hours -= 24
               } else if (that.hours >= 24) {
                    that.hours -= 24
               }

               var dh = 0
               var dm = 0

               if (this.hours > that.hours){
                    dh = this.hours - that.hours
                    dm = this.minutes - that.minutes
               } else {
                    dh = that.hours - this.hours
                    dm = that.minutes - this.minutes
               }

               val diff = dh * 60 + dm
               val newHour = diff / 60
               val newMins = diff % 60
               new SimpleTimeAux(newHour, newMins)
          }

          def equals(that: SimpleTimeAux): Boolean = {
               this.hours == that.hours &&
                    this.minutes == that.minutes
          }
     }



     def main(args: Array[String]) {
          val t1 = new SimpleTimeAux(10, 30)
          val t2 = new SimpleTimeAux(9, 40)
          assert(t1.subtract(t2).equals(new SimpleTimeAux(0, 50)))
          assert(t2.subtract(t1).equals(new SimpleTimeAux(0, 50)))

          val st2 = new SimpleTimeAux(10, 30).subtract(new SimpleTimeAux(9, 45))
          assert(st2.equals(new SimpleTimeAux(0, 45)))

          // note using auxiliary constructor
          val st3 = new SimpleTimeAux(9, 30).subtract(new SimpleTimeAux(10))
          assert(st3.equals(new SimpleTimeAux(0, 30)))

          val st4 = new SimpleTimeAux(12, 47).subtract(new SimpleTimeAux(5, 50))
          assert(st4.equals(new SimpleTimeAux(6, 57)))

          val st5 = new SimpleTimeAux(18, 7).subtract(new SimpleTimeAux(11, 45))
          assert(st5.equals(new SimpleTimeAux(6, 22)))

          val st6 = new SimpleTimeAux(24, 23).subtract(new SimpleTimeAux(17, 12))
          assert(st6.equals(new SimpleTimeAux(16, 49)))

          val st7 = new SimpleTimeAux(28, 10).subtract(new SimpleTimeAux(14, 5))
          assert(st7.equals(new SimpleTimeAux(9, 55)))
     }
}
