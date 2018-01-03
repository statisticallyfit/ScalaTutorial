package Books.ScalaForTheImpatient_CayHorstmann.chapter14_patternMatchingWithCaseClasses

/**
  *
  */
object Exercise4_ItemMultiple {



     sealed abstract class Item
     case class Article(description:String, price:Double) extends Item
     case class Bundle(description:String, discount:Double, items:Item*) extends Item
     case class Multiple(amount:Int, item:Item) extends Item

     def price(item: Item): Double = {
          item match {
               case Article(_, p) => p
               case Bundle(_, disc, its@_*) => its.map(price).sum - disc
               case Multiple(amt, it) => price(it) * amt
          }
     }

     def round(num:Double, spots:Int=0): Double ={
          val multiplier: Double = (1 +: Array.fill[Int](spots)(0)).mkString.toDouble
          Math.round(num * multiplier) / multiplier
     }

     def main(args: Array[String]) {

          val bundle = Bundle("Father's day special", 20.0,
               Article("Scala for the Impatient", 39.95),
               Bundle("Anchor Distillery Sampler", 10.0,
                    Article("Old Potrero Straight Rye Whiskey", 79.95),
                    Article("Junipero Gin", 32.95)))

          val multipleArticle = Multiple(4, Article("shoes", 6.78))
          val multipleBundle = Multiple(5, Bundle("jam", 3.00, multipleArticle))

          assert(round(price(bundle), 2) == 122.85)
          assert(round(price(multipleArticle), 2) == 27.12)
          assert(round(price(multipleBundle), 2) == 120.6)
     }
}
