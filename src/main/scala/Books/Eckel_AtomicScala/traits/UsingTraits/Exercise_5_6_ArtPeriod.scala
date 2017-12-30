package Books.Eckel_AtomicScala.traits.UsingTraits



object Exercise_5_6_ArtPeriod extends App {

     class ArtPeriodMakeMethod1 {
          trait ArtPeriod {
               def period(year:Int) = {
                    year match {
                         case y: Int if y < 1300 => "Pre-Renaissance"
                         case y: Int if y >= 1300 && y < 1600 => "Renaissance"
                         case y: Int if y >= 1600 && y < 1700 => "Baroque"
                         case y: Int if y >= 1700 && y < 1790 => "Late Baroque"
                         case y: Int if y >= 1790 && y < 1880 => "Romanticism"
                         case y: Int if y >= 1880 && y < 1970 => "Modern"
                         case _ => "Contemporary"
                    }
               }
          }

          class Painting(title:String, year:Int)
               extends ArtPeriod {
               def period:String = period(year)
          }

          val painting = new Painting("The Starry Night", 1889)
          println(painting.period)  // "Modern"
     }



     class ArtPeriodMakeMethod2 {

          trait ArtPeriod {
               val year:Int
               def period(/*year: Int*/) = {
                    year match {
                         case y if y < 1300 => "Pre-Renaissance"
                         case y if y <= 1599 => "Renaissance"
                         case y if y <= 1699 => "Baroque"
                         case y if y <= 1789 => "Late Baroque"
                         case y if y <= 1880 => "Romanticism"
                         case y if y <= 1970 => "Modern"
                         case _ => "Contemporary"
                    }
               }
          }

          // todo: why does Painting need Period(year) so that ArtPeriod gets the year argument?
          class Period(val year:Int)
          class Painting(title:String, year:Int) extends Period(year) with ArtPeriod

          val painting = new Painting("The Starry Night", 1889)
          println(painting.period) // Modern
     }


     new ArtPeriodMakeMethod1
     new ArtPeriodMakeMethod2
}