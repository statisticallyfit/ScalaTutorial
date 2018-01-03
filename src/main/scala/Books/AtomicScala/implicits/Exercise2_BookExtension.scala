package Books.AtomicScala.implicits

/**
  *
  */
object Exercise2_BookExtension {


     case class Book(title:String)

     object BookExtension {
          implicit class Ops(book:Book) {
               def categorize(category:String) =
                    s"$book, category: $category"
               def ISBN(category:String, isbn:String) =
                    s"${categorize(category)}, ISBN: $isbn"
          }
     }

     def main(args: Array[String]) {
          import BookExtension._
          val result = Book("Life of Bees").categorize("Insects")
          assert(result == "Book(Life of Bees), category: Insects")

          val result2 = Book("The Jungle Book").ISBN("Plants", "12354")
          assert(result2 == "Book(The Jungle Book), category: Plants, ISBN: 12354")
     }
}
