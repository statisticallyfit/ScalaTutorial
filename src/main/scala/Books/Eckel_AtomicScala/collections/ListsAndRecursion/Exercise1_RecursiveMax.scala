package Books.Eckel_AtomicScala.collections.ListsAndRecursion

/**
  *
  */
object Exercise1_RecursiveMax {


     def recursiveMax(list: Seq[Int]): Int ={
          def max(list: Seq[Int], acc: Int): Int ={
               if(list.isEmpty) acc
               else max(list.tail, if(list.head > acc) list.head else acc)
          }
          max(list.tail, list.head)
     }

     def main(args: Array[String]) {

          val values = List(1,-90,0,18,34,7,3,1,6,-7,-8,3,33,-5,1,89,472,-9,500)
          assert(recursiveMax(values) == 500)

          val values2 = List(1000,-90,0,18,34,7,3,1,6,-7,-8,3,33,-5,1,89,472,-9)
          assert(recursiveMax(values2) == 1000)
     }
}
