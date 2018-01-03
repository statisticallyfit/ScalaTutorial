package Books.ScalaForTheImpatient_CayHorstmann.chapter21_implicits

import java.awt.Point
import language.implicitConversions
import scala.util.Sorting.quickSort


object Exercise6_PointLex {

     class RichPoint(val p: Point) extends Ordered[Point]{
          def compare(that:Point):Int ={
               if(p.x == that.x){
                    p.y - that.y
               } else {
                    p.x - that.x
               }
          }
     }

     implicit def pointToRichPoint(p:Point): RichPoint = new RichPoint(p)

     def main(args: Array[String]): Unit = {

          val points = Array(new Point(1,2), new Point(3,4), new Point(2,5), new Point(3,8), new Point(3,-1))
          //val sortedPoints = Array(new Point(1,2), new Point(2,5), new Point(3,-1), new Point(3,4), new Point(3,8))
          quickSort(points)
          points.foreach(println)
     }
}
