package Books.Eckel_AtomicScala.patternmatching.PatternMatchingWIthTypes

/**
  *
  */
object Exercise5_ForecastRange {

     def forecast(percent: Int): String ={
          percent match {
               case _ if percent > 80 => "Sunny"
               case _ if percent > 50 => "Mostly Sunny"
               case _ if percent > 20 => "Partly Sunny"
               case _ if percent > 0 => "Mostly Cloudy"
               case 0 => "Cloudy"
               case _ => "Unknown"
          }
     }

     def main(args: Array[String]) {
          assert(forecast(100) == "Sunny")
          assert(forecast(81) == "Sunny")
          assert(forecast(80) == "Mostly Sunny")
          assert(forecast(51) == "Mostly Sunny")
          assert(forecast(50) == "Partly Sunny")
          assert(forecast(21) == "Partly Sunny")
          assert(forecast(20) == "Mostly Cloudy")
          assert(forecast(1) == "Mostly Cloudy")
          assert(forecast(0) == "Cloudy")
          assert(forecast(-1) == "Unknown")

          val sunnyData = Vector(100,80,50,20,0,15)
          sunnyData.map(d => forecast(d)).foreach(println)
     }
}
