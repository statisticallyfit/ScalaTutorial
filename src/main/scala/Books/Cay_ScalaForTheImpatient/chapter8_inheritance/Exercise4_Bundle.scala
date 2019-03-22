package Books.Cay_ScalaForTheImpatient.chapter8_inheritance

/**
  *
  */
object Exercise4_Bundle {

     abstract class Item {

          def price: Double
          def description: String
          override def toString = "%s(%s: %f)".format(this.getClass.getSimpleName, description, price)
     }


     class SimpleItem(override val price: Double,
                      override val description: String
                     ) extends Item


     class Bundle extends Item {
          private var items: List[Item] = List()
          def add(item: Item) = {items = item :: items}
          def price = items.map(_.price).sum
          def description = items.map(_.description).mkString(", ")
     }




     def main(args: Array[String]): Unit = {
          var l: List[Item] = List(
               new SimpleItem(23.20, "carrot"),
               new SimpleItem(4.35, "apple")
          )

          val b = new Bundle
          b.add(new SimpleItem(3.20, "orange"))
          b.add(new SimpleItem(5, "strawberry"))

          l = b :: l
          l.foreach(println(_))
     }
}
