package Books.Cay_ScalaForTheImpatient.chapter11_operators

import scala.collection.mutable.ArrayBuffer

/**
  *
  */
object Exercise8_Matrix {

     object Utils {

          def dotProd(vector1: Vector[Int], vector2: Vector[Int]): Vector[Int] ={
               vector1.zip(vector2).map(v => v._1 * v._2)
          }
     }

     object Matrix {
          def apply(vecOfVecs: Vector[Vector[Int]]): Matrix = new Matrix(vecOfVecs)
     }



     class Matrix(val matrix: Vector[Vector[Int]]) {

          val rows = matrix.length
          val cols = matrix(0).length


          def rowAt(index: Int): Vector[Int] = this.matrix(index)
          def colAt(index: Int): Vector[Int] = {
               val column: ArrayBuffer[Int] = ArrayBuffer()
               (0 until rows) foreach {i => column += this.matrix(i)(index)}
               column.toVector
          }


          def +(that: Matrix): Matrix ={
               require(this.rows == that.rows)
               require(this.cols == that.cols)

               Matrix(matrix.zip(that.matrix)
                    .map(vecPair => vecPair._1.zip(vecPair._2)
                         .map(valPair => valPair._1 + valPair._2)))
          }

          def -(that: Matrix): Matrix ={
               require(this.rows == that.rows, "ERROR: num rows must be equal")
               require(this.cols == that.cols, "ERROR: num cols must be equal")

               Matrix(matrix.zip(that.matrix)
                    .map(vecPair => vecPair._1.zip(vecPair._2)
                         .map(valPair => valPair._1 - valPair._2)))
          }


          def *(that: Matrix): Matrix ={
               require(this.cols == that.rows, "ERROR: num cols of matrix1 must equal num rows of matrix2")

               var newMat: ArrayBuffer[Vector[Int]] = ArrayBuffer()
               var tempRow: ArrayBuffer[Int] = ArrayBuffer()

               for(i <- 0 until this.rows){
                    for(j <- 0 until that.cols){
                         tempRow += Utils.dotProd(this.rowAt(i), that.colAt(j)).sum
                    }
                    newMat += tempRow.toVector
                    tempRow.clear
               }
               Matrix(newMat.toVector)
          }


          def *(scalar: Int): Matrix ={
               var newMat: ArrayBuffer[Vector[Int]] = ArrayBuffer()
               var tempRow: ArrayBuffer[Int] = ArrayBuffer()

               for(i <- 0 until rows){
                    for(j <- 0 until cols){
                         tempRow += this.matrix(i)(j) * scalar
                    }
                    newMat += tempRow.toVector
                    tempRow.clear()
               }
               Matrix(newMat.toVector)
          }

          def ==(that: Matrix): Boolean = this.matrix == that.matrix

          override def toString: String = {
               val biggestNumLen: Int = this.matrix.flatten.map(_.toString.length).foldLeft(0)(_ max _)
               // means there should be at least 1 space between each number or more if the num
               // length is less than biggest num length
               val leftFrontSpace: Int = 1 + biggestNumLen

               this.matrix.map(_.map(v => v.toString.reverse
                    .padTo(leftFrontSpace, " ")
                    .reverse.mkString(""))
                    .mkString)
                    .mkString("\n")
          }
     }


     def main(args: Array[String]) {

          val m1: Matrix = Matrix(Vector(
               Vector(1,2,3,4),
               Vector(5,6,7,8),
               Vector(9,10,11,12)
          ))

          val m2: Matrix = Matrix(Vector(
               Vector(4,9,3,0),
               Vector(2,8,-3,1),
               Vector(6,9,3,3)
          ))

          val addResult: Matrix = Matrix(Vector(
               Vector(5,11,6,4),
               Vector(7,14,4,9),
               Vector(15,19,14,15)
          ))

          val subResult: Matrix = Matrix(Vector(
               Vector(-3,-7,0,4),
               Vector(3,-2,10,7),
               Vector(3,1,8,9)
          ))

          val m3: Matrix = Matrix(Vector(
               Vector(-1,0,2,3,1),
               Vector(8,3,-1,0,2),
               Vector(0,2,5,4,2),
               Vector(-3,2,0,-1,5)
          ))
          // 3 x 5
          val multResult: Matrix = Matrix(Vector(
               Vector(3, 20, 15, 11, 31),
               Vector(19, 48, 39, 35, 71),
               Vector(35, 76, 63, 59, 111)
          ))

          val scalarResult: Matrix = Matrix(Vector(
               Vector(8, 18, 6, 0),
               Vector(4, 16, -6, 2),
               Vector(12, 18, 6, 6)
          ))

          val testPrint: Matrix = Matrix(Vector(
               Vector(5, 1000, 67),
               Vector(23, 1, 5),
               Vector(5007, 2001, 5362)
          ))

          println(m1 + "\n")
          println(multResult + "\n")
          println(testPrint + "\n")


          assert(m1 + m2 == addResult)
          assert(m1 - m2 == subResult)
          assert(m2 * 2 == scalarResult)
          assert(m1 * 1 == m1)
          assert(m2 * 1 == m2)
          assert(m3 * 1 == m3)
          assert(m1 * m3 == multResult)
     }
}
