package Books.AtomicScala.classes.BaseClassInitialization

/**
  *
  */
object Exercise5_Trip {


     class Trip(val origin:String, val dest:String,
                val startDate:String, val endDate:String){

          override def toString: String = {
               s"From $origin to $dest: $startDate to $endDate"
          }
     }

     class AirplaneTrip(origin:String, dest:String, startDate:String, endDate:String,
                        val flightMovie:String)
          extends Trip(origin, dest, startDate, endDate){

          override def toString: String = {
               s"On a flight from $origin to $dest, we watched $flightMovie"
          }
     }

     class CarTrip(val citiesPassed: List[String], startDate:String, endDate:String)
          extends Trip(citiesPassed.head, citiesPassed.last, startDate, endDate)


     def main(args: Array[String]) {

          val t = new Trip("Detroit", "Houston", "5/1/2012", "6/1/2012")
          val a = new AirplaneTrip("Detroit", "London", "9/1/1939", "10/31/1939", "Superman")

          val cities = List("Boston", "Albany", "Buffalo", "Cleveland", "Columbus",
               "Indianapolis", "St. Louis", "Kansas City", "Denver", "Grand Junction",
               "Salt Lake City", "Las Vegas", "Bakersfield", "San Francisco")

          val c = new CarTrip(cities, "6/1/2012", "7/1/2012")
          assert(c.origin == "Boston")
          assert(c.dest == "San Francisco")
          assert(c.startDate == "6/1/2012")
          assert(c.endDate == "7/1/2012")

          println("trip: " + t)
          assert(t.toString == "From Detroit to Houston: 5/1/2012 to 6/1/2012")

          println("air: " + a)
          assert(a.toString == "On a flight from Detroit to London, we watched Superman")

          println("car: " + c)
          assert(c.toString == "From Boston to San Francisco: 6/1/2012 to 7/1/2012")
     }
}
