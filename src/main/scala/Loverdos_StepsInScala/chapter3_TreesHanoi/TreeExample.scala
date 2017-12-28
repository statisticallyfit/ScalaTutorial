package Loverdos_StepsInScala.chapter3_TreesHanoi

/**
  *
  */

import scala.io.StdIn

object TreeExample {

     /** There is only one EmptyTree so make it case object not case class */
     /** NOTE: Binary search tree left subtree has values that are strictly less than values in right subtree.
       * And no value is duplicated
       * And all elements are sorted when printed using in order traversal */
     abstract class BinaryTree
     case object EmptyTree extends BinaryTree
     case class Node(elem: Int, left: BinaryTree, right: BinaryTree) extends BinaryTree {

          override def toString:String ={
               def staircase(count:Int, accPad:String, tree:BinaryTree): String ={
                    val pad:String = accPad + "\t" //"".padTo(count, "\t").mkString

                    /*tree match {
                         case Node(n, EmptyTree, EmptyTree) =>
                              s"Node($n, $pad$EmptyTree, $pad$EmptyTree)"
                         case Node(n, EmptyTree, rgt) =>
                              s"Node($n, $pad$EmptyTree, $pad${staircase(count + 1, pad, rgt)})"
                         case Node(n, lft, EmptyTree) =>
                              s"Node($n, $pad${staircase(count + 1, pad, lft)}, $pad$EmptyTree)"
                         case Node(n, lft, rgt) =>
                              s"Node($n, $pad${staircase(count + 1, pad, lft)}, $pad${staircase(count + 1, pad, rgt)})"
                    }*/
                    tree match {
                         case EmptyTree => "EmptyTree"
                         case Node(n, lft, rgt) =>
                              s"Node($n, $pad${staircase(count + 1, pad, lft)}, $pad${staircase(count + 1, pad, rgt)})"
                    }
               }

               staircase(1, "\n", this)
          }
     }



     /** left subtree, topmost node, right subtree */
     def inOrderFlatten(binaryTree: BinaryTree): List[Int] = {
          binaryTree match {
               case EmptyTree => List()
               case Node(n,left,right) => inOrderFlatten(left) ::: List(n) ::: inOrderFlatten(right)
          }
     }

     /** topmost node, left subtree, right subtree */
     def preOrderFlatten(binaryTree: BinaryTree): List[Int] ={
          binaryTree match {
               case EmptyTree => List()
               case Node(n,left,right) => List(n) ::: preOrderFlatten(left) ::: preOrderFlatten(right)
          }
     }

     /** HELP
       * right subtree, topmost node, left subtree
       * OR IS IT instead
       * left, right, topmost? */
     def postOrderFlatten(binaryTree: BinaryTree): List[Int] ={
          binaryTree match {
               case EmptyTree => List()
               case Node(n,left,right) => postOrderFlatten(left) ::: postOrderFlatten(right) ::: List(n)
          }
     }


     def depth(binaryTree: BinaryTree): Int ={
          binaryTree match {
               case EmptyTree => 0
               case Node(_, EmptyTree, right) => 1 + depth(right)
               case Node(_, left, EmptyTree) => 1 + depth(left)
               case Node(_, left, right) => 1 + Math.max(depth(left), depth(right))
          }
     }

     def mkTree(list:List[Int]): BinaryTree ={
          def insert(input:Int, binaryTree: BinaryTree):BinaryTree ={
               binaryTree match {
                    case EmptyTree => Node(input, EmptyTree, EmptyTree)
                    case tree@Node(currNode,left,right) =>
                         if(input < currNode){
                              Node(currNode, insert(input, left), right)
                         } else if(input > currNode){
                              Node(currNode, left, insert(input, right))
                         } else tree //used to return EmptyTree but I want it to just return the tree.
               }
          }

          list match {
               case Nil => EmptyTree
               case x::xs => insert(x, mkTree(xs))
          }
     }


     def reflect(binaryTree: BinaryTree): BinaryTree ={
          binaryTree match {
               case EmptyTree => EmptyTree
               case Node(n, left, right) => Node(n, reflect(right), reflect(left))
          }
     }

     def isEmpty(binaryTree: BinaryTree): Boolean ={
          binaryTree match {
               case EmptyTree => true
               case _ => false
          }
     }

     def root(binaryTree: BinaryTree): Option[Int] ={
          binaryTree match {
               case Node(n,_,_) => Some(n)
               case EmptyTree => None
          }
     }

     //mkTree inorder, preorder, postorder
     //inorder dfs, inorder bfs | preorder dfs, preorder bfs | postorder dfs, postorder bfs
     //inorder ACTION, preorder ACTION, postorder ACTION

     def getInputs: List[Int] ={
          StdIn.readLine().split(' ').flatMap(_.toCharArray).filter(_.isDigit).map(_.toString.toInt).toList
     }



     def main(args: Array[String]) {

          val empty = EmptyTree
          val t1 = Node(4,
               Node(2,
                    Node(1, EmptyTree, EmptyTree),
                    Node(3, EmptyTree, EmptyTree)),
               Node(6,
                    Node(5, EmptyTree, EmptyTree),
                    Node(7, EmptyTree, EmptyTree)))

          val t2 = Node(10,
               Node(9,
                    Node(8,
                         t1,
                         EmptyTree),
                    EmptyTree),
               EmptyTree)


          Console.println("t1 inorder: " + inOrderFlatten(t1))
          Console.println("t1 preorder: " + preOrderFlatten(t1))
          Console.println("t1 postorder: " + postOrderFlatten(t1))
          println()
          Console.println("t2 inorder: " + inOrderFlatten(t2))
          Console.println("t2 preorder: " + preOrderFlatten(t2))
          Console.println("t2 postorder: " + postOrderFlatten(t2))
          println()
          Console.println("t1 depth: " + depth(t1))
          Console.println("empty depth: " + depth(empty))
          Console.println("t2 depth: " + depth(t2))
          println()

          val l1 = List(1,2,3,4,5)
          val l2 = List(7,2,9,1,8,4,4,5,3,7,6,1,10,9)
          val l3 = List(1,2,3,4,5,6,7,8,9,10)
          Console.println(mkTree(l1))
          println()
          Console.println("Tree list 2: " + "\n" + mkTree(l2))
          println()
          Console.println(inOrderFlatten(mkTree(l3)))
          Console.println(inOrderFlatten(mkTree(l2)))
          Console.println(preOrderFlatten(mkTree(l3)))
          Console.println(preOrderFlatten(mkTree(l2)))
          println()
          Console.println(reflect(t1))
     }
}
