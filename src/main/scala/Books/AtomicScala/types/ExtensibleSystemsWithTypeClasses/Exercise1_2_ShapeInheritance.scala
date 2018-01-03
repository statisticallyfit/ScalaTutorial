package Books.AtomicScala.types.ExtensibleSystemsWithTypeClasses


import scala.math.Pi

object Exercise1_2_ShapeInheritance {

     trait Shape {
          def area: Double
          override def toString: String
     }

     case class Circle(radius: Double) extends Shape {
          def area = 2 * Pi * radius
          override def toString = s"Circle has radius $radius"
     }

     case class EqTriangle(side:Double) extends Shape {
          def area = (Math.sqrt(3)/4) * side * side
          override def toString = s"EqTriangle has side $side"
     }

     case class Rectangle(length:Double, width:Double) extends Shape {
          def area = length * width
          override def toString = s"Rectangle has length $length and width $width"
     }

     def showArea(s:Shape) = f"$s area: ${s.area}%.2f"


     def main(args: Array[String]) {
          val shapes = Vector(Circle(2.2), EqTriangle(3.9), Circle(4.5), Rectangle(3.0, 4.0))
          shapes.foreach(println)
          shapes.map(s => showArea(s)).foreach(println)
     }
}
