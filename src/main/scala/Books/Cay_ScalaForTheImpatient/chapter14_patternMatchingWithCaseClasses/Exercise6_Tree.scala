package Books.Cay_ScalaForTheImpatient.chapter14_patternMatchingWithCaseClasses

/**
  *
  */
object Exercise6_Tree {


     sealed abstract class BinaryTree
     case class Leaf(value:Int) extends BinaryTree
     case class Node(left:BinaryTree, right:BinaryTree) extends BinaryTree

     def leafSum(tree: BinaryTree): Int ={
          tree match {
               case Leaf(n) => n
               case Node(left, right) => leafSum(left) + leafSum(right)
               case _ => 0
          }
     }

     def main(args: Array[String]) {

          val tree = Node(Leaf(1), Node(
               Node(Leaf(3),
                    Node(
                         Node(Leaf(2), Leaf(9)),
                         Leaf(7))),
               Node(Leaf(5), Leaf(4))))

          assert(leafSum(tree) == 31)
     }
}
