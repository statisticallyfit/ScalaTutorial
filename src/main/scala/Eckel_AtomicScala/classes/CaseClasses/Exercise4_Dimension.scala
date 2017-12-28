package Eckel_AtomicScala.classes.CaseClasses

/**
  *
  */
object Exercise4_Dimension {


     case class Dimension(var height: Int, var width: Int)

     def main(args: Array[String]) {
          val dim = Dimension(7, 5)
          assert(dim.height == 7 && dim.width == 5)
          dim.height = 10
          dim.width = 24
          assert(dim.height == 10 && dim.width == 24)
     }
}
