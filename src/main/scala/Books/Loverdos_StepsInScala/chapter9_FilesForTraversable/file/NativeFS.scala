package Books.Loverdos_StepsInScala.chapter9_FilesForTraversable.file

import Books.Loverdos_StepsInScala.chapter9_FilesForTraversable.path.Path

import java.io.{File => JavaFile}
import scala.language.postfixOps




object NativeFS extends VFS[NativeFile] {

     def mkPath(name: String) = Path(name)
     def name = "NativeFS"
     override def newFile(path: Path) = NativeFile(path)
     def resolve(path: Path) = {
          val file = newFile(path)
          if(file.exists) Some(file)
          else None
     }
     def newTempFile(prefix: String, suffix: String) =
          NativeFile(JavaFile.createTempFile(prefix, suffix))

     def roots = JavaFile.listRoots.map { root =>
          NativeFile(root) } toList

     def container = None
}