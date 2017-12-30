package Books.Eckel_AtomicScala.traits.TaggingTraitsAndCaseObjects



object Exercise1_Color extends  App {


     sealed trait Color
     case object Red extends Color
     case object Green extends Color
     case object Blue extends Color
     case object Purple extends Color


     object Color {
          val values = Vector(Red, Green, Blue, Purple, Red)
     }

     def display(c:Color) = c match {
          case Red => s"It's $c"
          case Green => s"It's $c"
          case Blue => s"It's $c"
          case Purple => s"It's $c"
     }

     println(Color.values.map(display))

}
// note: exercise 4: adding another Red to tagging trait COlor doesn't produce error:
// we can add it safely to the Vector