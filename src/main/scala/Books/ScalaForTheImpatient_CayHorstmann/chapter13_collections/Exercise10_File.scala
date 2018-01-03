package Books.ScalaForTheImpatient_CayHorstmann.chapter13_collections

/**
  *
  */
//source: https://github.com/hempalex/scala-impatient/blob/master/Chapter13/10.scala
object Exercise10_File /*extends App*/ {

     val path: String = "/datascience/projects/statisticallyfit/github/learningprogramming/Scala/ScalaTutorial/src/main/scala/bookScalaImpatient/chapter12_collections/"
     val fileLines = io.Source.fromFile(path + "athertonFileReading.txt").mkString

     def printMills(msg: String)(block: => Unit) {
          val start = System.currentTimeMillis()
          block
          val end = System.currentTimeMillis()
          println(msg.format(end-start))
     }

     def main(args: Array[String]) {

          printMills("Using mutable collection: %d ms") {
               val freq = new collection.mutable.HashMap[Char, Int]
               for (letter <- fileLines){
                    val currFreq = freq.getOrElse(letter, 0)
                    freq(letter) = currFreq + 1
               }
               println(freq.toSeq.sorted)
          }


          printMills("Using immutable collection: %d ms") {
               val freq = fileLines.map(c => (c, 1)).groupBy(_._1).map(x => (x._1, x._2.length))
               println(freq.seq.toSeq.sorted)
          }

          //help understand better
          /*printMills("Using mutable parallel collection: %d ms") {

               val freq = str.par.aggregate(new collection.immutable.HashMap[Char, Int])(
                    (x, c) => x + (c -> (x.getOrElse(c, 0) + 1)),
                    (map1, map2) => map1 ++ map2.map {
                         case (k,v) => k -> (v + map1.getOrElse(k,0)) }
               )

               println(freq.toSeq.sorted)
          }*/
     }
}
