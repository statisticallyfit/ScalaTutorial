package Books.AtomicScala.implicits





case class Book(title:String)

//note doing it with value class
package object BookExtension {

     implicit class Ops(val book:Book) extends AnyVal {
          def categorize(category:String) =
               s"$book, category: $category"
     }
}

object Exercise3_BookExtension {
     def main(args: Array[String]) {
          import BookExtension._
          val result = Book("United We Spy").categorize("Spy")
          assert(result == "Book(United We Spy), category: Spy")
     }
}