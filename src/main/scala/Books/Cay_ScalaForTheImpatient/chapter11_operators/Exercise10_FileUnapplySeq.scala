package Books.Cay_ScalaForTheImpatient.chapter11_operators

/**
  *
  */
object Exercise10_FileUnapplySeq {

     class RichFile(val file: String)

     object RichFile {
          def apply(path: String, name: String, ext: String): RichFile = new RichFile(path + name + ext)
          def unapplySeq(rf: RichFile): Option[Seq[String]] ={
               if(rf.file.trim == "") None
               else {
                    val seq: Seq[String] = rf.file.trim.split("/").toSeq
                    if(seq.head == "") Some(seq.tail)
                    else Some(seq)
               }
          }
     }

     def main(args: Array[String]) {
          val richFile: RichFile = RichFile("/datascience/projects/statisticallyfit/github/learningprogramming/Scala" +
               "/ScalaTutorial/src/main/scala/bookScalaImpatient/chapter10_operators/",
               "bananas", ".txt")

          Console.println(richFile.file)

          // note using unapplySeq
          val RichFile(seq@_*) = richFile //RichFile.unapplySeq(richFile)
          // note help todo why doesn't explicit way work?
          //val RichFile(seq2@_*) = RichFile.unapplySeq(richFile)
          seq.foreach(println)
     }

}
