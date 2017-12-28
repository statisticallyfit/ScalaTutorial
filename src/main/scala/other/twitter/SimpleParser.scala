package other.twitter

case class SimpleParsed(id: Long, text: String)

class SimpleParser {

     val tweetRegex = "\"id\":(.*),\"text\":\"(.*)\"".r

     def parse(str: String) = {
          tweetRegex.findFirstMatchIn(str) match {
               case Some(m) => {
                    val id = str.substring(m.start(1), m.end(1)).toInt
                    val text = str.substring(m.start(2), m.end(2))
                    Some(SimpleParsed(id, text))
               }
               case _ => None
               // @todo: what is difference between _: and just _ ?
               // @todo: does _ mean just not the expected value or, just a placeholder
               // @todo: .... when not needing to name a variable?
          }
     }
}


object SimpleParserRunner {
     def main(args: Array[String]): Unit = {
          val tweet = """{"id":1,"text":"foo"}"""
          val parser = new SimpleParser
          println(parser.parse(tweet))
     }
}
