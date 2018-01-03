package Books.StepsInScala_Loverdos.chapter11_Trees.iter.node.iterableProvider

import Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.file.VFile


class VFileChildrenProvider[T <: VFile[T]] extends NodeChildrenProvider[T]{
     def childrenOf(node: T) = node.children.iterator
}
