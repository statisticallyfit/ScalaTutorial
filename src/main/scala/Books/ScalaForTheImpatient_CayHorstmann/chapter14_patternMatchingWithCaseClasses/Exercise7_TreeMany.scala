package Books.ScalaForTheImpatient_CayHorstmann.chapter14_patternMatchingWithCaseClasses

/**
  *
  */
object Exercise7_TreeMany {

     sealed abstract class BinaryTree
     case class Leaf(value:Int) extends BinaryTree
     case class Node(ts:BinaryTree*) extends BinaryTree

     // help why doesn't this work? List(ts).map(t => leafSum(t)).sum
     def leafSum(tree: BinaryTree): Int ={
          tree match {
               case Leaf(n) => n
               case Node(ts@_*) => ts.map(t => leafSum(t)).sum
               case _ => 0
          }
     }

     def main(args: Array[String]) {

          val tree = Node(Leaf(1),
               Leaf(10),
               Node(Leaf(7),
                    Leaf(2),
                    Node(
                         Node(Leaf(3),
                         Leaf(8),
                         Node(
                              Node(Leaf(2),
                              Leaf(9)),
                              Leaf(7))),
                         Node(Leaf(5),
                              Leaf(4))
          )))

          println(leafSum(tree))
          assert(leafSum(tree) == 58)
     }
}
