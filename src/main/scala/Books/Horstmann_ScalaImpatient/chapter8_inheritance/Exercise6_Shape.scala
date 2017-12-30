package Books.Horstmann_ScalaImpatient.chapter8_inheritance

/**
  *
  */
object Exercise6_Shape {

     class Point (val x: Int, val y: Int ){
          override def toString() = "Point(%d, %d)".format(x, y)
     }


     abstract class Shape {
          def centerPoint: Point
          override def toString = "%s(Center: %s)".format(this.getClass.getSimpleName, centerPoint.toString)
     }


     class Rectangle (val topLeft: Point, val bottomRight: Point) extends Shape {
          override val centerPoint = new Point(
               (topLeft.x + bottomRight.x) / 2,
               (topLeft.y + bottomRight.y) / 2)
     }

     class Circle (override val centerPoint: Point, val radius: Int) extends Shape




     def main(args: Array[String]) {
          val r = new Rectangle(new Point(0, 0), new Point(4, 4))
          println(r)

          val c = new Circle(new Point(3, 3), 10)
          println(c)
     }
}
