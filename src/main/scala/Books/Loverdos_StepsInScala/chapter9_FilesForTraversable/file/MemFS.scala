/*
package Loverdos_StepsInScala.chapter9_FilesForTraversable.file

import Loverdos_StepsInScala.chapter9_FilesForTraversable.path._

import scala.collection.mutable


class MemFS(val name: String) extends VFS[MemFile] {
     private[this] val MemRoot: MemFile =
          new MemoryFolder(this, Path.UnixPath("/"))

     protected[file] val cache =
          mutable.HashMap[Path, MemFile](MemRoot.path -> MemRoot)


     def mkPath(name: String) = Path.UnixPath("/" + name)

     def roots = MemRoot :: Nil

     override def root = MemRoot

     def container = None

     def newTempFile(prefix: String, suffix: String) =
          throw new UnsupportedOperationException

     override def newFolder(path: Path) =
          cache.getOrElseUpdate(path, new MemoryFolder(this, path))

     override def newFile(path: Path) =
          cache.getOrElseUpdate(path, new MemoryFile(this, path))

     override def resolve(path: Path) = cache.get(path)

     override def toString = "MemFS(" + name + ")"
}
*/
