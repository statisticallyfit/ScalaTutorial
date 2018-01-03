package Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.file

import Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.path.Path

/**
  *
  */
object VFS {
     type AnyFile = VFile[_] // don't care about exact type VFile contains
}

//VFS = virtual file system, provides
// VFile = virtual file, provided by VFS
trait VFS[T <: VFile[T]] {

     def name: String

     def mkPath(name: String): Path

     def roots: List[T]

     def root = roots(0)

     def container: Option[VFS.AnyFile]

     def isContained = container.isDefined

     def newTempFile(prefix:String, suffix:String): T

     def newFile(path: Path): T

     def newFile(path: String): T = newFile(mkPath(path))

     def newFolder(path: Path) = newFile(path)

     def newFolder(path: String): T = newFolder(mkPath(path))

     def resolve(path: Path): Option[T]

     def resolve(path: String): Option[T] = resolve(mkPath(path))

     def contains(path: Path) = resolve(path).isDefined

     def contains(path: String) = resolve(path).isDefined

     override def toString = "VFS(" + name + ")"
}