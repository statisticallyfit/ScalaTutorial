package Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.tests

import Books.StepsInScala_Loverdos.chapter9_FilesForTraversable.path._

import scala.io.StdIn


object TestingPaths {

     def testS(s: String) = testP(Path(s))

     def testP(p: Path): Unit = {
          println("path.class      : " + p.getClass.getName)
          println("path            : " + p)
          println("path.parent     : " + p.parent)
          println("path.name       : " + s"'${p.name}'") //or '" + p.name + "'"
          println("path.relative   : " + p.relative)
          println("path.parts      : " + p.parts)
     }

     def main(args: Array[String]) {
          val fileName:String = StdIn.readLine("filename?: ")
          testP(fileName) //note implicit conversion comes into play

          Console.println()

          //help why doesn't implicit come into play, why have to make conversion of 'datascience' manually?
          val mine:Path = Path("datascience") / "projects" / "statisticallyfit" / "github" / "Scala" / "Paths.scala"
          testP(mine)
     }
}
