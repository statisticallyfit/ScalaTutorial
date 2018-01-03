package Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.file

import Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.path.Path


class NativeFile(val path: Path) extends VFile[NativeFile] {

     private[this] lazy val nfile = new java.io.File(path.fullName)

     override def asNative = Some(this)

     def /(that: Path) = new NativeFile(this.path / that)

     def /(that: String) = new NativeFile(this.path / that)

     def exists = nfile.exists

     def isFile = nfile.isFile

     def isFolder = nfile.isDirectory

     def inputStream ={
          if(isFile)
               Some(new java.io.FileInputStream(path.fullName))
          else None
     }

     def outputStream ={
          if(isFile)
               Some(new java.io.FileOutputStream(path.fullName))
          else None
     }

     def children = nfile.listFiles match {
          case null => Array[NativeFile]()
          case array => array.map { file =>
               NativeFile(file.getPath)
          }
     }

     def childrenNames = nfile.list match {
          case null => Array[String]()
          case array => array
     }

     def nativeJavaFile = nfile
}


object NativeFile {
     def apply(nfile: java.io.File) =
          new NativeFile(NativeFS.mkPath(nfile.getPath))

     def apply(path: Path) = new NativeFile(path)

     def apply(path: String) =
          new NativeFile(NativeFS.mkPath(path))
}