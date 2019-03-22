package Books.Loverdos_StepsInScala.chapter11_Trees.iter.node.iterableProvider

import Books.Loverdos_StepsInScala.chapter9_FilesForTraversable.file.VFile


class VFileChildrenProvider[T <: VFile[T]] extends NodeChildrenProvider[T]{
     def childrenOf(node: T) = node.children.iterator
}
