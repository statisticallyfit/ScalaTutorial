package Books.ScalaForTheImpatient_CayHorstmann.chapter4_mapsAndTuples

import java.util.Calendar

import scala.collection.mutable

/**
  *
  */
object Exercise6_MapCalendar {

     def main(args: Array[String]) {
          val days = mutable.LinkedHashMap(
               "Monday" -> Calendar.MONDAY, "Tuesday" -> Calendar.TUESDAY, "Wednesday" -> Calendar.WEDNESDAY,
               "Thursday" -> Calendar.THURSDAY, "Friday" -> Calendar.FRIDAY, "Saturday" -> Calendar.SATURDAY,
               "Sunday" -> Calendar.SUNDAY)

          days foreach println
     }
}
