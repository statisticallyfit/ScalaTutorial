package Books.ScalaForTheImpatient_CayHorstmann.chapter3_arrays

/**
  *
  */
object Exercise9_TimeZoneSort {

     def stripFilterSortTimeZones(): Array[String] = {
          val zones = java.util.TimeZone.getAvailableIDs()
          zones.filter(_.startsWith("America/")).map(_.drop("America/".size)).sorted
     }

     def main(args: Array[String]) {
          println(stripFilterSortTimeZones().mkString(", "))
     }
}
