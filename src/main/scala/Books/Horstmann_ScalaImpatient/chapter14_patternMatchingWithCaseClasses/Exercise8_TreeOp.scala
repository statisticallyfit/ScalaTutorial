package Books.Horstmann_ScalaImpatient.chapter14_patternMatchingWithCaseClasses


object Exercise8_TreeOp {

     sealed abstract class BinaryTree
     case class Leaf(value:Int) extends BinaryTree
     case class Node(op:Char, ts:BinaryTree*) extends BinaryTree

     def eval(tree: BinaryTree): Int ={
          tree match {
               case Leaf(n) => n
               case Node(op, ts@_*) => op match {
                    case '+' => ts.map(eval).sum
                    case '-' => -ts.map(eval).sum //.reduceLeft((acc,y) => acc - y)
                    case '*' => ts.map(eval).product
               }
               case _ => 0
          }
     }

     def main(args: Array[String]) {

          val tree =
               Node('+',
                    Node('*', Leaf(3), Leaf(8)),
                    Leaf(2),
                    Node('-', Leaf(5)))

          println(eval(tree))
          assert(eval(tree) == 21)

          val biggerTree =
               Node('+',
                    Node('-', Leaf(12), Leaf(4)),
                    Leaf(3),
                    Node('*',
                         Leaf(3),
                         Node('-',
                              Leaf(5),
                              Node('+',
                                   Leaf(10),
                                   Leaf(1))),
                         Node('+',
                              Leaf(2),
                              Leaf(1))))

          println(eval(biggerTree))
          assert(eval(biggerTree) == -157)
     }
}
