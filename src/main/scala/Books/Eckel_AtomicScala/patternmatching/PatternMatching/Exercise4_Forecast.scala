package Books.Eckel_AtomicScala.patternmatching.PatternMatching

/**
  *
  */
object Exercise4_Forecast {

     def forecast(percent: Int): String ={
          percent match {
               case 100 => "Sunny"
               case 80 => "Mostly Sunny"
               case 50 => "Partly Sunny"
               case 20 => "Mostly Cloudy"
               case 15 => "Unknown"
               case 0 => "Cloudy"
          }
     }

     def main(args: Array[String]) {
          assert(forecast(100) == "Sunny")
          assert(forecast(80) == "Mostly Sunny")
          assert(forecast(50) == "Partly Sunny")
          assert(forecast(20) == "Mostly Cloudy")
          assert(forecast(15) == "Unknown")
          assert(forecast(0) == "Cloudy")

          val sunnyData = Vector(100,80,50,20,0,15)
          sunnyData.map(d => forecast(d)).foreach(println)
     }
}
