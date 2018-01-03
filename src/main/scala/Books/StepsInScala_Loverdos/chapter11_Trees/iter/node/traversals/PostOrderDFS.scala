package Books.StepsInScala_Loverdos.chapter11_Trees.iter.node.traversals

import Books.StepsInScala_Loverdos.chapter11_Trees.iter.node.iterableProvider._

/**
  * Left subtree, right subtree, topmost
  */
class PostOrderDFS[T](start:T, provider: NodeChildrenProvider[T])
     extends NodeIteratorSkeleton(start, provider) {

     private[this] val toExplore = new LIFOStore[T].addNode(start)
     private[this] var processed = Set[T]()

     protected def computeNext: Boolean ={
          toExplore.remove match {
               case nodeOpt@Some(node) => {
                    if(processed.contains(node)){
                         //a node is post processed only if children are processed
                         nextMaybe = nodeOpt
                         //this allows for memory efficiency
                         processed -= node
                         true
                    } else {
                         //add again before children to obtain the post-order property
                         toExplore.addNode(node)
                         toExplore.addChildrenOf(node, provider)
                         processed += node

                         computeNext
                    }
               }
               case None => {
                    nextMaybe = None
                    false
               }
          }
     }
}
