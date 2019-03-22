package Books.Cay_ScalaForTheImpatient.chapter10_traits


import java.awt.geom._




object Exercise1_RectangleLike extends App {


     trait RectangleLike {

          this: RectangularShape =>

          def translate(dx: Double, dy: Double): Unit = {
               setFrame(getX + dx, getY + dy, getWidth, getHeight)
          }

          def grow(h: Double, v: Double): Unit ={
               setFrame(getX, getY, getWidth + h, getHeight + v)
          }
     }

     trait Show {
          this: RectangularShape =>

          def show: Unit ={
               println(s"x-coord: ${this.getX}")
               println(s"y-coord: ${this.getY}")
               println(s"width:   ${this.getWidth}")
               println(s"height:  ${this.getHeight}")
          }

          def show(msg: String): Unit ={
               println(msg)
               show
          }
     }


     // note works with self-type because Ellipse2D is abstract class that
     // is subclass of RectangularShape class, the self-type
     val egg = new Ellipse2D.Double(5,10,20,30) with RectangleLike with Show
     egg.show("\nBefore: ")
     egg.translate(10, -10)
     egg.grow(10, 20)
     egg.show("\nAfter: ")
}