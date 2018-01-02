package TEMP.AdHocPolyBlogTryOuts.apacheExample



//note source: https://tylersommer.com/ad-hoc-polymorphism-in-scala

/**
  *
  */
object Best_ApacheLogURL {



     //Given two unrelated types, perhaps representing Apache log data:
     case class ApacheLog(date: String, url: String)
     // and the aggregate count of that data
     case class URLCounts(url: String, count: Int)
     object URLCounts {
          // We use the implicit evidence syntax here, allowing us to use
          // methods defined on HasURL[T}.
          def fromLogs[T](logs: Seq[T])(implicit ev: HasURL[T]): Seq[URLCounts] = {
               logs.map(line => URLCounts(ev.url(line),1))
                    .groupBy(_.url)
                    .map{case (url, counts) => URLCounts(url, counts.map(_.count).sum)}.toSeq
          }
     }



     // A Conversion represents a sale that occurred on a merchant's website.
     case class Conversion(url: String, orderNumber: String, purchaseAmount: Int)

     // A Converter is a generic converter that can convert sequences of one type to another.
     class Converter[T, R](implicit relation: Relation[T, R]) {
          def convert(seq: Seq[T]): Seq[R] = relation.convert(seq)
     }



     //HasURL is a generalized type representing something with a URL
     trait HasURL[T] {
          def url(t: T): String
     }
     object HasURL {
          // Define how to get a URL out of an ApacheLog.
          /*implicit object ApacheLogsHaveURL extends HasURL[ApacheLog] {
               def url(t: ApacheLog): String = t.url
          }*/

          //The syntax is a little different here because we are in the top-most scope; this
          // could (and probably should) be defined in the hasURL companion object.
          implicit def conversionsHaveURL: HasURL[Conversion] = new HasURL[Conversion] {
               def url(t: Conversion): String = t.url
          }
     }


     // A Relation represents the relation between two types, how to convert
     trait Relation[T, R] {
          def convert(t: Seq[T]): Seq[R]
     }
     object Relation {
          /*implicit object Logs2Counts extends Relation[ApacheLog, URLCounts] {
               def convert(t: Seq[ApacheLog]): Seq[URLCounts] = URLCounts.fromLogs(t)
          }*/
          // In this example we use context bounds instead of implicit evidence since
          // we don't need the evidence, so there's no point having it passed in
          // as an implicit parameter.
          implicit def HasURL2Counts[T: HasURL]: Relation[T, URLCounts] = new Relation[T, URLCounts] {
               def convert(t: Seq[T]): Seq[URLCounts] = URLCounts.fromLogs(t)
          }
     }


     /**
       * NOTE: any sequence of type T that has implicit evidence for HasURL[T]
       * note"  can be converted into a sequence of URLCounts!
       * note: This is the true power of ad-hoc polymorphism.
       *
       */
     def main(args: Array[String]) {

          val input = Seq(
               Conversion("http://buycool.stuff/order-confirm", "2134", 4999), // Prices are in cents, btw.
               Conversion("http://buycool.stuff/order-confirm", "1235", 3563),
               Conversion("http://lotsofneat.stuff/thank-you", "523a1", 6799))

          /////////URLCounts.fromLogs(input).foreach(c => println(s"${c.url}: ${c.count}"))
          val converter = new Converter[Conversion, URLCounts]
          converter.convert(input).foreach(c => println(s"${c.url}: ${c.count}"))
     }
}
