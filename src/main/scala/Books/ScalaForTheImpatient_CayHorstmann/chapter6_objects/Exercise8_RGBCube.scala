package Books.ScalaForTheImpatient_CayHorstmann.chapter6_objects

object Exercise8_RGBCube {

     object RGBCube extends Enumeration {
          val black = Value(0x000000, "Black")
          val red = Value(0xff0000, "Red")
          val green = Value(0x00ff00, "Green")
          val blue = Value(0x0000ff, "Blue")
          val yellow = Value(0xffff00, "Yellow")
          val magenta = Value(0xff00ff, "Magenta")
          val cyan = Value(0x00ffff, "Cyan")
          val white = Value(0xffffff, "White")
     }

     def main(args: Array[String]) {
          //println(for(rgb <- RGBCube.values) yield (rgb.id, rgb))
          val result = RGBCube.values.map(v => (v.id, v))
          Console.println(result)
     }
}