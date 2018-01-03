package Books.StepsInScala_Loverdos.chapter11_Trees.iter.node.iterableProvider

// note the IterableNode approach was wrapping the tree so that we were getting double
//structure in memory! Too expensive, so just create a simple way of showing children nodes.
//note now for each node type there is ONE NodeChildrenProvider which is valid for all
//instances of the same node type.

trait NodeChildrenProvider[T] {
     def childrenOf(node: T): Iterator[T]
}
