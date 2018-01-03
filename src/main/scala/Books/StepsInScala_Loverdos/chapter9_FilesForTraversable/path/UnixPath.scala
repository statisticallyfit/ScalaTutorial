package Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.path

/**
  *
  */

class UnixPath(val fullName: String) extends Path {
     /** Removes root prefixes */
     def relative ={
          new UnixPath(
               if(isAbsolute)
                    fullName.substring(1)
               else
                    fullName
          )
     }

     def parent ={
          if(isRoot)
               EmptyPath
          else {
               fullName.lastIndexOf('/') match {
                    case -1 => EmptyPath
                    case 0 => new UnixPath("/")
                    case n => new UnixPath(fullName.substring(0, n))
               }
          }
     }

     def name = fullName.substring(fullName.lastIndexOf('/') + 1)

     override def parts = {
          val theParts = fullName.split('/') map {
               case "" => "/" // the root must be transformed
               case s => s
          }
          theParts.toList  //to avoid postfix warnings from compiler
     }

     def isUNC = false

     def isRoot = "/" == fullName

     def isEmpty = false

     override def isAbsolute = '/' == fullName.charAt(0)

     def /(that: String) = Path.combine(this, that)

     def /(that: Path) = Path.combine(this, that)
}
