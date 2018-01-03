/*
package Loverdos_StepsInScala.chapter9_FilesForTraversable.file

import Loverdos_StepsInScala.chapter9_FilesForTraversable.path.Path

/**
  *
  */
class MemoryFile(memFS:MemFS, val path: Path) extends MemFile {

     private[this] var bytes = new Array[Byte](0)
     def exists = true
     def isFile = true
     def isFolder = false
     def children = Nil
     def childrenNames = Nil
     def /(that: String) =
          combined(memFS, this.path / that, that.endsWith("/"))
     def /(that: Path) =
          combined(memFS, this.path / that, false)
     def inputStream =
          Some(new java.io.ByteArrayInputStream(bytes))
     def outputStream =
          Some(new java.io.ByteArrayOutputStream {
          })

     override def close = {
          super.close
          MemoryFile.this.bytes = this.toByteArray
     }
}
*/
