package Books.StepsInScala_Loverdos.chapter11_Trees.iter.node.traversableProvider

/**
  * We are encodin the foreach method directly so basically creating
  * our own traversable just in the same spirit
  * Note this means: give me a node and I will process all of its children
  * using function f()
  */
trait TraversableProvider[T] {
     def foreach(start: T, f: T => Unit): Unit
}
