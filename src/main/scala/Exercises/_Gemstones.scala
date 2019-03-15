package Exercises

/**
  *
  */
object _Gemstones {


     //find what is common in the gemstones (strings of letters)
     case class Gemstone(elements:String)

     def commonElements(gemstones: Seq[Gemstone]): Int = {
          gemstones.map(elem => elem.elements).reduceLeft(_ intersect _).length
          //or
          /*gemstones.tail
               .foldLeft(gemstones.head.elements)((acc,y) =>
                    acc.intersect(y.elements)).length*/
     }

     /*def getGemstones(): Seq[Gemstone] ={

     }

     def main(args: Array[String]) {

     }*/
}
