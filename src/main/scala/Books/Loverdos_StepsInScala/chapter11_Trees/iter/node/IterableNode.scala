package Books.Loverdos_StepsInScala.chapter11_Trees.iter.node

import language.implicitConversions

/*trait IterableNode[T] extends Iterable[IterableNode[T]] {
     def childrenNodes: Iterable[IterableNode[T]]
     def elements = childrenNodes.elements
}


object IterableNode {

     implicit def vfileToIterableNode[T <: VFile[T]](f: T): IterableNode[T] ={
          new IterableNode[T] {
               def childrenNodes = f.children.map(vfileToIterableNode[T])
          }
     }
}*/



