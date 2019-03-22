package Books.Cay_ScalaForTheImpatient.chapter8_inheritance

/**
  *
  */
object Exercise7_Square {


     class Square (x: Int, y: Int, width: Int) extends java.awt.Rectangle {
          def this(width: Int) = this(0, 0, width)
          def this() = this(0, 0, 0)
          override def toString() = "Square(%d, %d, %d)".format(x, y, width)
     }


     def main(args: Array[String]) {
          val s1 = new Square
          println(s1)
          val s2 = new Square(80)
          println(s2)
          val s3 = new Square(1, 2, 400)
          println(s3)
     }
}
