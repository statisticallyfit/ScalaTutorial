package Books.ScalaForTheImpatient_CayHorstmann.chapter6_objects

/**
  *
  */
object Exercise4_Point {


     class Point(x: Int, y: Int) { override def toString() = "Point(" + x + ", " + y + ")" }

     object Point {
          def apply(x: Int, y: Int) = new Point(x, y)
     }

     def main(args: Array[String]) {
          println(Point(3, 4))
     }
}
