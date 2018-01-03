package Books.ScalaForTheImpatient_CayHorstmann.chapter11_operators

/**
  *
  */
object Exercise6_ASCIIArt {


     class ASCIIArt(val art: String){

          override def toString: String = this.art

          // note combines them horizontally
          def +(that: ASCIIArt): ASCIIArt = {
               ASCIIArt(art.split("\n")
                    .zip(that.art.split("\n"))
                    .map(t => t._1 + "  " + t._2)
                    .mkString("\n"))
          }

          // note combines them vertically
          def ^(that: ASCIIArt): ASCIIArt ={
               ASCIIArt(art + that.art)
          }
     }

     object ASCIIArt {
          def apply(art: String): ASCIIArt = new ASCIIArt(art)
          def unapply(aSCIIArt: ASCIIArt): Option[String] = Some(aSCIIArt.art)
     }


     def main(args: Array[String]) {

          val kitty = new ASCIIArt(" /\\_/\\\n( ' ' )\n(  -  )\n | | | \n(__|__)\n")
          val speechBubble = new ASCIIArt("   -----\n / Hello \\\n<  Scala |\n \\ Coder /\n   -----\n")

          //note can't use, too many spaces
          /*val speechBubble = new ASCIIArt(
          """
                -----
              / Hello \
             <  Scala |
              \ Coder /
                -----
          """.stripMargin)*/

          println(kitty)
          println(speechBubble)

          println("Combine horizontally: ")
          println(kitty + speechBubble)

          println("\n\nCombine vertically: ")
          println(kitty ^ speechBubble)
     }
}
