package Books.Loverdos_StepsInScala.chapter11_Trees.iter.node.iterableProvider

import scala.collection.immutable.Queue
import scala.collection.mutable.ListBuffer

/**
  * We need some buffering because BFS and DFS won't report the node
  * as soon as it is found, so we need somewhere to keep it
  * help why do we really need this, how will it remember better than the tree itself?
  */
trait NodeStore[T] {

     def addNode(node: T): NodeStore[T]

     def addChildrenOf(node: T,
                       provider: NodeChildrenProvider[T]
                      ): NodeStore[T]

     /** Returns and removes first stored node. First is different depending on if we used
       * FIFO (Queue) for BFS or a LIFO (Stack) for DFS */
     def remove: Option[T]
}


//need concrete implementations for NodeStore
class LIFOStore[T] extends NodeStore[T] {

     private var stack = List[T]()

     def addNode(node: T): NodeStore[T] = {stack = node :: stack; this}

     //need to reverse children because we are adding to a stack (adding to the top always)
     def addChildrenOf(node: T, provider: NodeChildrenProvider[T]) = {
          val children = provider.childrenOf(node).toList.reverse
          children.foreach(child => addNode(child))
          this
     }

     def remove: Option[T] = stack match {
          case Nil => None
          case node :: rest => {
               stack = rest
               Some(node)
          }
     }
}


class FIFOStore[T] extends NodeStore[T] {

     private var queue = Queue[T]()

     def addNode(node: T): NodeStore[T] ={
          queue = queue.enqueue(node)
          this
     }

     def addChildrenOf(node: T, provider: NodeChildrenProvider[T]): NodeStore[T] ={
          val children = provider.childrenOf(node)
          val buf = new ListBuffer[T]

          children.foreach {
               child =>
                    addNode(child)
                    buf += child
          }
          buf.clear
          this
     }

     def remove: Option[T] = {
          if(queue.isEmpty) None
          else {
               val (end, newQueue) = queue.dequeue
               queue = newQueue
               Some(end)
          }
     }
}