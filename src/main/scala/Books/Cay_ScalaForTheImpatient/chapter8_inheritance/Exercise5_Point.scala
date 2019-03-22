package Books.Cay_ScalaForTheImpatient.chapter8_inheritance

/**
  *
  */
object Exercise5_Point {


     // todo: why "Type mismatch" error when I put DOuble for x and y?
     class Point (x: Int, y: Int ){
          override def toString() = "Point(%d, %d)".format(x, y)
     }


     class LabeledPoint(label: String, x: Int, y: Int) extends Point(x, y){
          override def toString() = "LabeledPoint(%s, %d, %d)".format(label, x, y)
     }


     def main(args: Array[String]) {

          val p = new Point(8, 9)
          println(p)
          val l = new LabeledPoint("Black Thursday", 1929, 230)
          println(l)
     }
}
