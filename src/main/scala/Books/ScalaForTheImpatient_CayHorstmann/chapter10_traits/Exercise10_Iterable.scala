package Books.ScalaForTheImpatient_CayHorstmann.chapter10_traits

import java.io.{FileInputStream, InputStream}

/**
  *
  */
object Exercise10_Iterable {


     trait IterableInputStream extends InputStream with Iterable[Byte]{

          def iterator = new Iterator[Byte] {
               def hasNext = if(available() > 0) true else false
               def next = read().toByte //.asInstanceOf[Byte]
          }
     }

     def main(args: Array[String]) {
          val fileIn = new FileInputStream("randomFilename.txt") with IterableInputStream

          // todo help understand better how this works
          for(x <- fileIn) print(x.toChar)
     }
}
