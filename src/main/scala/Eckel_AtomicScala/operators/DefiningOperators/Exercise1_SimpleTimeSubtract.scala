package Eckel_AtomicScala.operators.DefiningOperators

/**
  *
  */
object Exercise1_SimpleTimeSubtract {


     class SimpleTime(var hours: Int, var minutes: Int=0){


          // note only supports military time :from
          // 00:00 on day 1 to 23.59 on day 1 (24:00 on day 1 is 0:00 on day 2)
          // note if given hour 24 or greater, then makes it 0 and keeps minutes then does subtraction
          // Assume we keep the same day
          // example: 24:23 subtract 17:12 equals ==> 4:49
          def -(that: SimpleTime): SimpleTime = {

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
               new SimpleTime(newHour, newMins)
          }

          def ==(that: SimpleTime): Boolean = {
               this.hours == that.hours &&
                    this.minutes == that.minutes
          }
     }

     def main(args: Array[String]) {
          val t1 = new SimpleTime(10, 30)
          val t2 = new SimpleTime(9, 40)
          assert(t1 - t2 == new SimpleTime(0, 50))
          assert(t2 - t1 == new SimpleTime(0, 50))

          val st2 = new SimpleTime(10, 30) - new SimpleTime(9, 45)
          assert(st2 == new SimpleTime(0, 45))

          val st3 = new SimpleTime(9, 30) - new SimpleTime(10)
          assert(st3 == new SimpleTime(0, 30))

          val st4 = new SimpleTime(12, 47) - new SimpleTime(5, 50)
          assert(st4 == new SimpleTime(6, 57))

          val st5 = new SimpleTime(18, 7) - new SimpleTime(11, 45)
          assert(st5 == new SimpleTime(6, 22))

          val st6 = new SimpleTime(24, 23) - new SimpleTime(17, 12)
          assert(st6 == new SimpleTime(16, 49))

          val st7 = new SimpleTime(28, 10) - new SimpleTime(14, 5)
          assert(st7 == new SimpleTime(9, 55))
     }

}
