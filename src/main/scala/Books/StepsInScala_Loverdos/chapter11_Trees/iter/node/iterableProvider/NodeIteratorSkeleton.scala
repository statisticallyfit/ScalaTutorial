package Books.StepsInScala_Loverdos.chapter11_Trees.iter.node.iterableProvider

abstract class NodeIteratorSkeleton[T](start: T, provider: NodeChildrenProvider[T])
     extends Iterator[T] {

     // None <==> no next value has been computed
     /** Note declared protected so that implementations can change its value **/
     protected var nextMaybe: Option[T] = None

     /** Actual workhorse: computes next children if there are actual
       * children, and sets nextOpt accordingly */
     protected def computeNext: Boolean

     def hasNext =
          if (nextMaybe.isDefined) true
          else computeNext

     def next =
          if(hasNext) {
               val result = nextMaybe.get
               nextMaybe = None
               result
          } else throw new NoSuchElementException
}
