package Books.Eckel_AtomicScala.collections.ListsAndRecursion

/**
  *
  */
object Exercise2_3_RecursiveSum {


     def sumIt(toSum: Seq[Int]): Int ={
          def sum(toSum: Seq[Int], acc:Int): Int ={
               if(toSum.isEmpty) acc
               else {
                    println(s"recursing with sum=$acc, list=$toSum")
                    sum(toSum.tail, acc + toSum.head)
               }
          }
          sum(toSum.tail, toSum.head)
     }

     def sumItFast(toSum: Seq[Int]): Int ={
          toSum.reduceLeft((acc, y) => acc + y)
     }

     def main(args: Array[String]) {
          Console.println(sumIt(List(10,20,30,40,50,-90)))
          Console.println(sumItFast(List(10,20,30,40,50,-90)))
     }
}
