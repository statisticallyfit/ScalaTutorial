/*
package Loverdos_StepsInScala.chapter9_FilesForTraversable.file

import Loverdos_StepsInScala.chapter9_FilesForTraversable.path.Path

/**
  *
  */
class MemoryFolder {

}
class MemoryFolder(memFS: MemFS, val path: Path) extends MemFile {

     def exists = true
     def isFile = false
     def isFolder = true
     def inputStream = None
     def outputStream = None

     def children = memFS.cache.values.filter(_.path.is)
          //memFS.cache.values.filter(_.path.isChildOf(path)).toList

     def childrenNames = children.map(_.name)

     def /(that: String) =
          combined(memFS, this.path / that, that.endsWith("/"))

     def /(that: Path) =
          combined(memFS, this.path / that, false)
}
*/
