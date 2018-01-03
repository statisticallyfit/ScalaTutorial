package Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.path

import scala.language.implicitConversions
/**
  * from chapter 8
  */

trait Path {
     def name: String
     def fullName: String
     /** Removes root prefixes */
     def relative: Path
     def isEmpty: Boolean
     /** E.g. for UNix, true if '/' is filename, while for Windows, true if A:\, B:\... */
     def isRoot: Boolean
     def isUNC: Boolean
     /** Mutual recursion like this only works if subclass implements at least one of them. */
     def isAbsolute: Boolean = !isRelative
     def isRelative: Boolean = !isAbsolute

     def /(that:String):Path
     def /(that:Path):Path

     def parent: Path

     def parts: List[String]

     def pathParts = parts.map(Path(_))

     override def toString = fullName
     override def hashCode = fullName.hashCode
     override def equals(any: Any) =
          any.isInstanceOf[Path] &&
               any.asInstanceOf[Path].fullName == this.fullName
}


object Path {

     def apply(path: String): Path ={
          if("" equals path) EmptyPath
          else if(isWindows) parseWinPath(path)
          else parseUnixPath(path)
     }

     def parseUnixPath(path: String): UnixPath =
          parseSimplePath(path, anySlash = false)

     def parseSimplePath(path: String, anySlash: Boolean) ={
          new UnixPath(
               removeRedundantSlashes(path,
                    0,
                    lastNonSlashIndex(path, anySlash), anySlash)
          )
     }

     def parseWinPath(path: String) = {
          val len = path.length
          val ch0 = path.charAt(0)
          len match {
               case 1 =>
                    parseSimplePath(path, true)
               case _ =>
                    val ch1 = path.charAt(1)
                    if(isAnySlash(ch0) && isAnySlash(ch1))
                         new UNCPath(
                              "//" +
                                   removeRedundantSlashes(
                                        path, 2, lastNonSlashIndex(path, true), true))

                    else if(len > 2 && isDriveLetter(ch0) && ':' == ch1 && isAnySlash(path.charAt(2))) {
                         val prefix = path.substring(0, 2) // C:
                         val rest = path.substring(2)
                         val suffix = removeRedundantSlashes(
                              rest, 0, lastNonSlashIndex(rest, true), true)
                         new DriveAbsPath(prefix + suffix)
                    } else
                         parseSimplePath(path, true)
          }
     }

     //UnixPath treated specially because it is the canonical one.
     object UnixPath {
          def apply(path:String):Path =
               if("".equals(path)) EmptyPath
               else parseUnixPath(path)
     }


     implicit def stringToPath(path: String): Path = this(path)

     val isWindows =
          System.getProperty("os.name")
               .toLowerCase.contains("windows")

     def isDriveLetter(ch: Char) =
          'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z'

     def isBackSlash(ch: Char) = '\\' == ch

     def isForwardSlash(ch: Char) = '/' == ch

     def isAnySlash(ch: Char) = isForwardSlash(ch) || isBackSlash(ch)

     /** Getting a function */
     def getSlashFunction(anySlash: Boolean) =
          if(anySlash) isAnySlash _ else isForwardSlash _

     /** Gets slash function and uses it to decide what is last index at which there is no slash */
     def lastNonSlashIndex(path: String, anySlash: Boolean) = {
          val isSlashF = getSlashFunction(anySlash)
          var index = path.length - 1
          while(index > 0 && isSlashF(path.charAt(index)))
               index -= 1
          index
     }

     //more functional version
     def lastNonSlashIndex2(path: String, anySlash: Boolean) = {
          val isSlashF = getSlashFunction(anySlash)
          def discoverIndex(index: Int): Int =
               if(index <= 0)
                    -1
               else if(isSlashF(path.charAt(index)))
                    discoverIndex(index - 1)
               else
                    index
          discoverIndex(path.length - 1)
     }

     //more functional
     //example: C:\\//usr///bin" => C:/usr/bin
     def removeAnyRedundantSlashes(path: String) ={
          path.replaceAll("[\\\\/]+", "/")
     }

     def removeRedundantSlashes(path: String, startIndex: Int,
                                endIndex: Int, anySlash: Boolean) = {
          val sb = new StringBuilder
          var index = startIndex
          var previousWasSlash = false
          val isSlashF = getSlashFunction(anySlash)
          while(startIndex <= index && index <= endIndex) {
               val ch = path.charAt(index)
               if(isSlashF(ch)) {
                    if(!previousWasSlash) {
                         sb.append('/')
                         previousWasSlash = true
                    }
               } else {
                    sb.append(ch)
                    previousWasSlash = false
               }
               index += 1
          }
          sb.toString
     }

     def combine(a: Path, b: String): Path = combine(a, Path(b))

     def combine(a: Path, b: Path) ={
          (a.isAbsolute, b.isAbsolute) match {
               case (_, true) => sys.error(
                    "Cannot concatenate path <%s> with absolute path <%s>".format(a, b))
               case (_, false) => Path(a.fullName + "/" + b.fullName)
          }
     }
}
