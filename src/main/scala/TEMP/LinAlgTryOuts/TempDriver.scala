package TEMP.LinAlgTryOuts
//import number._
//
//import scala.collection.mutable.ListBuffer
//
//
//
//class Matrix[N <: Number](list: ListBuffer[ListBuffer[N]]) (implicit n: NF)
//
//object Matrix {
//
//     def ZERO[N <: Number](numRows: Int, numCols: Int): Matrix[N] =
//          new Matrix(ListBuffer.fill[N](numCols, numRows)(n.ZERO[N]))
//
//}
//
//
//
//object Driver {
//
//
//     def main(args: Array[String]) {
//
//          val zeroes = Matrix.ZERO[Complex](3, 4)
//          println(zeroes)
//
//          val mat: Matrix[Complex] = new Matrix[Complex](
//               ListBuffer(
//                    ListBuffer(Complex(3, 5), Complex(1, 2)),
//                    ListBuffer(Complex(11,5), Complex(8, 74))
//          ))
//     }
//}

import spire.implicits._
import spire.math._

object TempDriver extends App {

}