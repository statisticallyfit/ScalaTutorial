package Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.path


/**
  * Needed for when we ask for parent of root path (empty)
  */
object EmptyPath extends Path {
     def name = ""
     def fullName = ""
     def relative = this
     def isEmpty = true
     def isRoot = false
     def isUNC = false
     override def isAbsolute = false
     override def isRelative = false
     def parts = Nil
     def parent = this
     def /(that: String) = Path(that)
     def /(that: Path) = that
}
