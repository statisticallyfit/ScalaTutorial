package Books.AtomicScala.classes.CaseClasses

/**
  *
  */
object Exercise6_SimpleTimeDefault {


     // note answer: yes case classes can have default arguments
     case class Time(var hours: Int, var minutes: Int=0){


          // note only supports military time :from
          // 00:00 on day 1 to 23.59 on day 1 (24:00 on day 1 is 0:00 on day 2)
          // note if given hour 24 or greater, then makes it 0 and keeps minutes then does subtraction
          // Assume we keep the same day
          // example: 24:23 subtract 17:12 equals ==> 4:49
          def -(that: Time): Time = {

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
               Time(newHour, newMins)
          }

          def ==(that: Time): Boolean = {
               this.hours == that.hours &&
                    this.minutes == that.minutes
          }
     }



     def main(args: Array[String]) {
          val t1 = Time(10, 30)
          val t2 = Time(9, 40)

          assert(t1 - t2 == Time(0, 50))
          assert(t2 - t1 == Time(0, 50))

          assert(Time(0, 30) - Time(9, 45) == Time(9, 15))
          assert(Time(9, 30) - Time(10) == Time(0, 30))
          assert(Time(12,47) - Time(5,50) == Time(6,57))
          assert(Time(18,7) - Time(11,45) == Time(6,22))
          assert(Time(24,23) - Time(17,12) == Time(16,49))
          assert(Time(28,10) - Time(14, 5) == Time(9, 55))
     }
}
