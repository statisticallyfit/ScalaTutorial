package Books.ScalaForTheImpatient_CayHorstmann.chapter5_classes

import scala.Predef._

/**
  *
  */
object Exercise3_Time {

     class Time (val hours: Int, val minutes: Int) {

          /*if(this.hours == other.hours){
          this.minutes < other.minutes
     }
     this.hours < other.hours*/
          def before(other: Time): Boolean = {
               this.hours * 60 + this.minutes <
                    other.hours * 60 + other.minutes
          }
     }

     def main(args: Array[String]) {
          val time = new Time(9, 30)
          assert(!time.before(new Time(9, 30)), "test 1") // false
          assert(time.before(new Time(10, 20)), "test 2") // true
          assert(!time.before(new Time(8, 10)), "test 3") // false
     }
}
