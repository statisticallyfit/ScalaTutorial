package Loverdos_StepsInScala.chapter9_FilesForTraversable.path

/**
  *
  */
trait WinPath extends Path


class UNCPath(val fullName: String) extends WinPath {
     def isRoot = 2 == fullName.length
     def isUNC = true
     override def isAbsolute = true
     def isEmpty = false
     def name = fullName.substring(fullName.lastIndexOf('/') + 1)
     def relative = new UnixPath(fullName.substring(2))
     def parent = {
          if(isRoot)
               EmptyPath
          else
               fullName.lastIndexOf('/') match {
                    case 1 => new UNCPath("//")
                    case n => new UNCPath(fullName.substring(0, n))
               }
     }

     override def parts = {
          if(isRoot) "//" :: Nil
          else "//" :: fullName.substring(2).split('/').toList
     }

     def /(that: String) = Path.combine(this, that)
     def /(that: Path) = Path.combine(this, that)
}



class DriveAbsPath(val fullName:String) extends WinPath {
     def isRoot = 3 == fullName.length
     def isUNC = false
     override def isAbsolute = true
     def isEmpty = false
     def name =
          if(isRoot) ""
          else fullName.substring(fullName.lastIndexOf('/') + 1)

     def relative = new UnixPath(fullName.substring(3))

     def parent = {
          if(isRoot)
               EmptyPath
          else {
               fullName.lastIndexOf('/') match {
                    case 2 => new DriveAbsPath(fullName.substring(0, 3))
                    case n => new DriveAbsPath(fullName.substring(0, n))
               }
          }
     }

     override def parts = {
          if(isRoot) fullName :: Nil
          else {
               fullName.substring(0, 3) ::
                    fullName.substring(3).split('/').toList
          }
     }

     def /(that: String) = Path.combine(this, that)
     def /(that: Path) = Path.combine(this, that)
}