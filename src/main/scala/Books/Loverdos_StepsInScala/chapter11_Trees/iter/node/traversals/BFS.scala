package Books.Loverdos_StepsInScala.chapter11_Trees.iter.node.traversals

import Books.Loverdos_StepsInScala.chapter11_Trees.iter.node.iterableProvider.{FIFOStore, NodeIteratorSkeleton, NodeChildrenProvider}

/**
  *
  */
class BFS[T](start: T, provider: NodeChildrenProvider[T])
     extends NodeIteratorSkeleton(start, provider) {

     private val toExplore = new FIFOStore[T].addNode(start)

     protected def computeNext: Boolean ={
          toExplore.remove match {
               case nodeOpt@Some(node) => {
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
