package Books.Cay_ScalaForTheImpatient.chapter10_traits

import java.io.InputStream
import java.io.BufferedInputStream
import java.io.FileInputStream
/**
  *
  */
object Exercise8_9_Buffering {

     trait Logger {
          def log(msg: String) {}
     }

     trait BufferedInput extends InputStream with Logger {

          private val buffIn = new BufferedInputStream(this)

          override def read(): Int = {
               val b = buffIn.read()
               if(b != -1) log("Read ... " + b.toChar)
               else log("EOF")
               b
          }

          override def log(msg: String) = println(msg)
     }


     def main(args: Array[String]) {

          // note help todo: why doesn't this read from thsi file? Why do random numbers come up?
          val path: String = "/src/main/scala/bookScalaImpatient/chapter9_traits/"
          val fileIn = new FileInputStream("randomFilename.txt") with BufferedInput

          var b: Int = 0
          while({b = fileIn.read(); b != -1 }){ }
     }
}
