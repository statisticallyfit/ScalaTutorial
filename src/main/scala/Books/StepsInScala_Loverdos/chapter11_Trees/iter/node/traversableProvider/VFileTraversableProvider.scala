package Books.StepsInScala_Loverdos.chapter11_Trees.iter.node.traversableProvider

import Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.file.VFile

/**
  *
  */
class VFileTraversableProvider[T <: VFile[T]]
     extends TraversableProvider[T] {

     /** means foreach(x => f(x)) OR  foreach(f(_)) */
     def foreach(start: T, f: T => Unit) =
          start.children.foreach(f)
}
