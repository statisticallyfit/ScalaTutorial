package Eckel_AtomicScala.traits.UsingTraits


object Exercise4_ArtPeriod extends App {


     trait ArtPeriod {
          def show(year: Int) = {
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

     val period = new Object with ArtPeriod

     assert(period.show(1400) == "Renaissance", "Test 1")
     assert(period.show(1650) == "Baroque", "Test 2")
     assert(period.show(1279) == "Pre-Renaissance", "Test 3")
}

