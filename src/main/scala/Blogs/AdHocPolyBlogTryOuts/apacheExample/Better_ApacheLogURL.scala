package Blogs.AdHocPolyBlogTryOuts.apacheExample


//note source: https://tylersommer.com/ad-hoc-polymorphism-in-scala

/**
  *
  */
object Better_ApacheLogURL {



     //Given two unrelated types, perhaps representing Apache log data:
     case class ApacheLog(date: String, url: String)
     // and the aggregate count of that data
     case class URLCounts(url: String, count: Int)
     object URLCounts {
          // A helper method to convert from logs into counts.
          def fromLogs(logs: Seq[ApacheLog]): Seq[URLCounts] = {
               logs.map(line => URLCounts(line.url, 1))
                    .groupBy(_.url)
                    .map{case (url, counts) => URLCounts(url, counts.map(_.count).sum)}.toSeq
          }
     }


     // A Relation represents the relation between two types, how to convert
     trait Relation[T, R] {
          def convert(t: Seq[T]): Seq[R]
     }
     object Relation {
          // Now define the relation between ApacheLog and URLCounts.
          implicit object Logs2Counts extends Relation[ApacheLog, URLCounts] {
               def convert(t: Seq[ApacheLog]): Seq[URLCounts] = URLCounts.fromLogs(t)
          }
     }



     // A Converter is a generic converter that can convert sequences of one type to another.
     class Converter[T, R](implicit relation: Relation[T, R]) {
          def convert(seq: Seq[T]): Seq[R] = relation.convert(seq)
     }



     def main (args: Array[String] ) {
          val input = Seq(
               ApacheLog("2017-02-08 19:45:22", "http://localhost/some-url"),
               ApacheLog("2017-02-08 19:46:04", "http://localhost/some-url"),
               ApacheLog("2017-02-08 19:46:53", "http://localhost/some-url"),
               ApacheLog("2017-02-08 19:46:57", "http://localhost/some-other-url"))

          val converter = new Converter[ApacheLog, URLCounts]
          converter.convert(input).foreach(c => println(s"${c.url}: ${c.count}"))
     }
}
