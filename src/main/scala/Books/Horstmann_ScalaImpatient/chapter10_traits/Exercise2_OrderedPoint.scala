package Books.Horstmann_ScalaImpatient.chapter10_traits


import java.awt.Point
import scala.math.Ordered


object Exercise2_OrderedPoint extends App {


     class OrderedPoint(initX: Int = 0, initY: Int = 0)
          extends Point(initX, initY) with Ordered[Point] {

          override def compare(that: Point): Int = {
               if (this.initX != that.getX) (this.initX - that.getX).toInt
               else {
                    if (this.initY != that.getY) (this.initY - that.getY).toInt
                    else 0
               }
          }
     }

     val p1 = new OrderedPoint(1, 1)
     val p2 = new OrderedPoint(4, -5)
     val p3 = new OrderedPoint(-3, 8)
     val p4 = new OrderedPoint(1, -1)
     val p5 = new OrderedPoint(1, 5)

     assert(p1.compare(p2) == -3, "Test 1")
     assert(p2.compare(p1) == 3, "Test 2")
     assert(p2.compare(p3) == 7, "Test 3")
     assert(p1.compare(p5) == -4, "Test 4")
     assert(p1.compare(p4) == 2, "Test 5")
}