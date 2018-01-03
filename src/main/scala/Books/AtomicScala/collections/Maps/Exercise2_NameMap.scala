package Books.AtomicScala.collections.Maps

/**
  *
  */
object Exercise2_NameMap {


     case class Name(first:String, last:String)

     def main(args: Array[String]) {

          val m = Map("sally@taylor.com" -> Name("Sally", "Taylor"),
               "jane@macintosh.com" -> Name("Jane", "Macintosh"))

          assert(m("sally@taylor.com") == Name("Sally", "Taylor"))
     }
}
