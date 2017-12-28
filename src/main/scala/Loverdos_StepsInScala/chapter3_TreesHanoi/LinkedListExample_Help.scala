package Loverdos_StepsInScala.chapter3_TreesHanoi

/**
  *
  */
object LinkedListExample_Help {


     abstract class LinkedList
     case object EmptyList extends LinkedList
     case class Node(elem:Int, next:LinkedList) extends LinkedList

     //create linked list
     def mkLinkedList(list: List[Int]): LinkedList ={
          list match {
               case Nil => EmptyList
               case x::xs => Node(x, mkLinkedList(xs))
          }
     }

     //delete particular element
     /*def delete(pos:Int, linkedList: LinkedList): LinkedList ={

          def remover(accPos:Int, ll:LinkedList): LinkedList ={
               ll match {
                    case EmptyList => EmptyList
                    case Node(elem, next) =>
                         if(pos == accPos) next
                         else
               }
          }
          linkedList match {
               case EmptyList => EmptyList
               case Node(elem, next) =>
                    if(elem == elemToDelete) next
                    else delete(elemToDelete, next)
          }
     }*/

     //add element in a specific position
     def add(elemToAdd:Int, pos:Int, linkedList: LinkedList): LinkedList ={

          def adder(accPos:Int, ll:LinkedList): LinkedList ={
               ll match {
                    case EmptyList => EmptyList
                    case node@Node(elem, next) =>
                         if(pos == accPos) Node(elemToAdd, node)
                         else Node(elem, adder(accPos + 1, next))
               }
          }
          adder(0, linkedList)
     }



     def main(args: Array[String]) {

          var link: LinkedList = Node(1, Node(2, Node(3, Node(4, Node(5, Node(6, Node(7, EmptyList)))))))

          Console.println(mkLinkedList((1 to 7).toList))
          assert(mkLinkedList((1 to 7).toList) == link)

          /*assert(delete(1, link) == Node(2, Node(3, Node(4, Node(5, Node(6, Node(7, EmptyList)))))))

          link = Node(1, Node(2, Node(3, Node(4, Node(5, Node(6, Node(7, EmptyList)))))))
          println(delete(2, link))
          assert(delete(2, link) == Node(1, Node(3, Node(4, Node(5, Node(6, Node(7, EmptyList)))))))

          link = Node(1, Node(2, Node(3, Node(4, Node(5, Node(6, Node(7, EmptyList)))))))
          assert(delete(7, link) == Node(1, Node(2, Node(3, Node(4, Node(5, Node(6, EmptyList)))))))*/


          link = Node(1, Node(2, Node(3, Node(4, Node(5, Node(6, Node(7, EmptyList)))))))
          assert(add(4, 0, link) == Node(4, Node(1, Node(2, Node(3, Node(4, Node(5,
               Node(6, Node(7, EmptyList)))))))))

          link = Node(1, Node(2, Node(3, Node(4, Node(5, Node(6, Node(7, EmptyList)))))))
          assert(add(99, 1, link) == Node(1, Node(99, Node(2, Node(3, Node(4, Node(5,
               Node(6, Node(7, EmptyList)))))))))

          link = Node(1, Node(2, Node(3, Node(4, Node(5, Node(6, Node(7, EmptyList)))))))
          assert(add(99, 6, link) == Node(1, Node(2, Node(3, Node(4, Node(5, Node(6,
               Node(99, Node(7, EmptyList)))))))))

          link = Node(1, Node(2, Node(3, Node(4, Node(5, Node(6, Node(7, EmptyList)))))))
          assert(add(99, 7, link) == Node(1, Node(2, Node(3, Node(4, Node(5, Node(6,
               Node(7, Node(99, EmptyList)))))))))
     }
}
