package Books.StepsInScala_Loverdos.chapter11_Trees.iter.node.traversals

import Books.StepsInScala_Loverdos.chapter11_Trees.iter.node.iterableProvider.{LIFOStore, NodeChildrenProvider, NodeIteratorSkeleton}

/**
  * Topmost, left subtree, right subtree
  */
class PreOrderDFS[T](start:T, provider: NodeChildrenProvider[T])
     extends NodeIteratorSkeleton(start, provider) {

     private val toExplore = new LIFOStore[T].addNode(start)

     protected def computeNext: Boolean ={
          toExplore.remove match {
               case nodeOpt@Some(node) =>{
                    nextMaybe = nodeOpt
                    toExplore.addChildrenOf(node, provider)
                    true
               }
               case None => {
                    nextMaybe = None
                    false
               }
          }
     }

}
