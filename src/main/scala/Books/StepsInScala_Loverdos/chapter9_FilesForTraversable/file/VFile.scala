package Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.file

import Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.path.Path

/**
  *
  */
trait VFile[T <: VFile[T]] { self: T =>
     def path: Path
     def name = path.name
     def isNative = this.isInstanceOf[NativeFile]
     def asNative: Option[NativeFile] = None
     def exists: Boolean
     def isFile: Boolean
     def isFolder: Boolean
     def children: Iterable[T]
     def childrenNames: Iterable[String]
     def /(that: String): T
     def /(that: Path): T
     def inputStream: Option[java.io.InputStream]
     def outputStream: Option[java.io.OutputStream]
     override def hashCode = path.hashCode
     override def equals(any: Any) =
          any.isInstanceOf[VFile[_]] &&
               any.asInstanceOf[VFile[_]].getClass == this.getClass &&
               any.asInstanceOf[VFile[_]].path == this.path
     override def toString = path.toString
}