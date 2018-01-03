package Books.ScalaForTheImpatient_CayHorstmann.chapter21_implicits

import java.awt.Point
import language.implicitConversions
import scala.util.Sorting.quickSort

/**
  *
  */
object Exercise7_PointLexOrigin {


     class LexicalOrdering(val p: Point) extends Ordered[Point]{
          def compare(that:Point):Int = if(p.x == that.x) p.y - that.y else p.x - that.x
     }

     class OriginOrdering(val p: Point) extends Ordered[Point] {
           def distanceToOrigin(p: Point): Double = Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2))
          def compare(that:Point):Int = (distanceToOrigin(p) - distanceToOrigin(that)).signum
     }

     //note todo seems we are using Ordering not Ordered in this class (mainly) because we need another api
     //to justify the use of the Ordered api, first used, and which we are forced to use in order to
     //call upon the implicit conversion.
     class OrderPointBy(val convert: Point => Ordered[Point]) extends Ordering[Point]{
          implicit def convertPointToSelectedOrdering(p:Point): Ordered[Point] = convert(p)
          // note we cannot pass in the implicit function right into compare, because
          // we must adhere to Ordered's api, so we must declare it in the class, (simpler)
          def compare(a:Point, b:Point): Int = a.compare(b)
     }


     def main(args: Array[String]) {
          val points = Array(new Point(1,2), new Point(3,4), new Point(2,5), new Point(4,1), new Point(3,-1))

          //help todo how does this work, because quicksort doesn't take any implicit arguments....???
          quickSort(points)(new OrderPointBy( (p:Point) => new LexicalOrdering(p) ))
          points.foreach(println)
          Console.println()

          quickSort(points)(new OrderPointBy( (p:Point) => new OriginOrdering(p) ))
          points.foreach(println)
     }
}
