/*
package Loverdos_StepsInScala.chapter9_FilesForTraversable.file

import Loverdos_StepsInScala.chapter9_FilesForTraversable.path.Path

/**
  *
  */
sealed trait MemFile extends VFile[MemFile] {

     protected def combined(memFS:MemFS, full:Path, endingSlash:Boolean):MemFile ={
          memFS.resolve(full) match {
               case Some(file) => file
               case None => {
                    if(endingSlash)
                         memFS.newFolder(full)
                    else
                         memFS.newFile(full)
               }
          }
     }
}
*/
