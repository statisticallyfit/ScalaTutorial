package Eckel_AtomicScala.types.ExtensibleSystemsWithTypeClasses

import scala.math.Pi
/**
  *
  */
object Exercise1_ShapeTypeClass {


     trait Calc[S] {
          def area(shape:S):Double
     }


     //note implicit means automatically inserted
     //during a function call.
     def showArea[S](shape:S)(implicit calc:Calc[S]) =
          f"$shape area: ${calc.area(shape)}%2.2f"



     case class Circle(radius:Double)
     case class EqTriangle(side:Double)
     case class Rectangle(length:Double, width:Double)

     //help why not use classes? Error: implicit arg not found, but with implicit obj it is found.
     implicit object CircleCalc extends Calc[Circle] {
          def area(shape:Circle) = 2 * shape.radius * Pi
          //help exercise 2 - how to add show without passing args?
          //override def toString = s"Circle has area ${area}"

     }

     implicit object EqTriangleCalc extends Calc[EqTriangle] {
          def area(shape:EqTriangle) = (Math.sqrt(3) / 4) * shape.side * shape.side
     }

     implicit object Rectangle extends Calc[Rectangle] {
          def area(shape:Rectangle) = shape.length * shape.width
     }




     def main(args: Array[String]) {

          val shapes = List(Circle(2.2), EqTriangle(3.9), Circle(4.5), Rectangle(3.0, 4.0))
          //todo cannot do this cause implicits aren't found...
          //shapes.map(s => showArea(s)).foreach(println)
          assert(showArea(Circle(2.2)) == "Circle(2.2) area: 13.82")
          assert(showArea(EqTriangle(3.9)) == "EqTriangle(3.9) area: 6.59")
          assert(showArea(Circle(4.5)) == "Circle(4.5) area: 28.27")
          assert(showArea(Rectangle(3.0, 4.0)) == "Rectangle(3.0,4.0) area: 12.00")

          val result = shapes.map {
               case s:Circle => showArea(s)      // These look the same ...
               case s:EqTriangle => showArea(s) // But now s is typed
               case s:Rectangle => showArea(s)
          }.toString
          assert(result == "List(" +
               "Circle(2.2) area: 13.82, " +
               "EqTriangle(3.9) area: 6.59, " +
               "Circle(4.5) area: 28.27, " +
               "Rectangle(3.0,4.0) area: 12.00)")

     }
}
