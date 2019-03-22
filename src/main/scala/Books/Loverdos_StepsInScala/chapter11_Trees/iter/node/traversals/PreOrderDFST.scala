package Books.Loverdos_StepsInScala.chapter11_Trees.iter.node.traversals

import Books.Loverdos_StepsInScala.chapter11_Trees.iter.node.traversableProvider.TraversableProvider


/**
  * Help understand better the roles of the different foreach's
  * implement postorder and inorder for DFST and BFST
  * implement inorderDFS
  * Note understand how to use this in practice - how to create a
  * traversable provider?
  */
class PreOrderDFST[T](start:T, provider: TraversableProvider[T])
     /*extends ToStringTraversable[T]*/ {

     def foreach(f: T => Unit): Unit = foreach(start, f)

     private def foreach(start: T, f: T => Unit): Unit = {
          f(start)
          provider.foreach(start, foreach(_, f))
     }
}
/*
abstract class BinaryTree[T]
case object EmptyTree extends BinaryTree
case class Node[T](elem: Int, left: BinaryTree, right: BinaryTree)
     extends BinaryTree[T]{
     /*with Traversable[Int] {

     def foreach(f: Int => Unit): Unit ={
          left.foreach(f)
          f(elem)
          right.foreach(f)
     }*/

     override def toString:String ={
          def staircase(count:Int, accPad:String, tree:BinaryTree): String ={
               val pad:String = accPad + "\t" //"".padTo(count, "\t").mkString

               tree match {
                    case Node(n, EmptyTree, EmptyTree) =>
                         s"Node($n, $pad$EmptyTree, $pad$EmptyTree)"
                    case Node(n, EmptyTree, rgt) =>
                         s"Node($n, $pad$EmptyTree, $pad${staircase(count + 1, pad, rgt)})"
                    case Node(n, lft, EmptyTree) =>
                         s"Node($n, $pad${staircase(count + 1, pad, lft)}, $pad$EmptyTree)"
                    case Node(n, lft, rgt) =>
                         s"Node($n, $pad${staircase(count + 1, pad, lft)}, $pad${staircase(count + 1, pad, rgt)})"
               }
          }

          staircase(1, "\n", this)
     }
}*/

/*
object obj {
     def main(args: Array[String]) {
          val pre = new PreOrderDFST[Int](4, _).foreach(int => int + 1)
          println(pre)
     }
}*/
