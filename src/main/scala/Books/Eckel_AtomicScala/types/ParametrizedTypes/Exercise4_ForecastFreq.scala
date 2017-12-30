package Books.Eckel_AtomicScala.types.ParametrizedTypes

/**
  *
  */
object Exercise4_ForecastFreq {

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

     def historicalData(weather: Seq[Int]): String ={
          val mp = weather
               .groupBy(x => identity(x))
               .collect{ case ((k, vs)) => (k, vs.length)}
          mp.map(p => forecast(p._1) -> p._2).mkString(", ")
     }

     def main(args: Array[String]) {
          val weather = Vector(100, 80, 20, 100, 20)
          Console.println(historicalData(weather))
     }
}
