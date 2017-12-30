package Books.Eckel_AtomicScala.collections.CombiningSeqsWithZip

/**
  *
  */
object Exercise1_2_3_Pairs {

     def pairUp(list: Seq[String]): Seq[(String, String)] ={
          val twoGroups = list.splitAt(list.length / 2)
          twoGroups._1.zip(twoGroups._2)
     }

     def main(args: Array[String]) {
          val people = Vector("Sally Smith", "Dan Jones",
               "Tom Brown", "Betsy Blanc", "Stormy Morgan", "Hal Goodsen")

          val pairs = pairUp(people)
          assert(pairs.toString == "Vector(" +
               "(Sally Smith,Betsy Blanc), " +
               "(Dan Jones,Stormy Morgan), " +
               "(Tom Brown,Hal Goodsen))")


          val morePeople = List("Sally Smith", "Dan Jones",
               "Tom Brown", "Betsy Blanc", "Stormy Morgan")

          val morePairs = pairUp(morePeople)
          assert(morePairs.toString == "List(" +
               "(Sally Smith,Tom Brown), " +
               "(Dan Jones,Betsy Blanc))")
     }
}
