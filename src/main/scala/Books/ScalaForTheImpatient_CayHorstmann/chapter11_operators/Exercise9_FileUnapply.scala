package Books.ScalaForTheImpatient_CayHorstmann.chapter11_operators

/**
  *
  */
object Exercise9_FileUnapply {

     class RichFile(val file: String)

     object RichFile {
          def apply(path: String, name: String, ext: String): RichFile = new RichFile(path + name + ext)
          def unapply(rf: RichFile): Option[(String, String, String)] = {
               val (path, nameAndExt) = rf.file.splitAt(rf.file.lastIndexOf("/"))
               val (name, ext) = nameAndExt.tail.splitAt(nameAndExt.tail.lastIndexOf("."))
               Some(path, name, ext)
          }
     }

     def main(args: Array[String]) {
          val richFile: RichFile = RichFile("/datascience/projects/statisticallyfit/github/learningprogramming/Scala" +
               "/ScalaTutorial/src/main/scala/bookScalaImpatient/chapter10_operators/",
               "bananas", ".txt")

          Console.println(richFile.file)

          // note using the unapply implicitly
          val RichFile(path, name, ext) = richFile
          Console.println(path)
          Console.println(name)
          Console.println(ext)

          // note using unapply explicitly
          Console.println(RichFile.unapply(richFile))
     }
}
