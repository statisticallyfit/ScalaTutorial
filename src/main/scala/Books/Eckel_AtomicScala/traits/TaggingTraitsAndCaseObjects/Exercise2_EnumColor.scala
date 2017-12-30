package Books.Eckel_AtomicScala.traits.TaggingTraitsAndCaseObjects



object Exercise2_EnumColor extends App {

     // todo:
     object EnumColor extends Enumeration {
          type EnumColor = Value
          val Red, Green, Blue = Value
     }

     println(EnumColor.Red) // Red
     println(EnumColor.Green) // Green
     println(EnumColor.Blue) // Blue
}
