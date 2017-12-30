package Books.Eckel_AtomicScala.patternmatching.PatternMatchingWIthTuples

/**
  *
  */
object Exercise1_2_3_4_5_ColorBlend {


     object Color extends Enumeration {
          type Color = Value
          val white, red, blue, yellow, purple,
          //   0      1     2      3      4
               green, orange, brown, magenta = Value
          //      5      6      7      8
          //note maxId = 9
     }

     import Color._

     def blend(a:Color, b:Color) ={
          (a,b) match {
               case _ if a == b => a
               case _ if a == `white` => b
               case _ if b == `white` => a
               case (`red`, `blue`) |
                    (`blue`, `red`) => purple
               case (`red`, `yellow`) |
                    (`yellow`, `red`) => orange
               case (`blue`, `yellow`) |
                    (`yellow`, `blue`) => green
               case (`magenta`, `yellow`) |
                    (`yellow`, `magenta`) => purple
               case (`magenta`, `red`)
                    | (`red`, `magenta`) => purple
               case (`brown`, _) |
                    (_, `brown`) => brown
               case _ => //interesting but not accurate:
                    Color((a.id + b.id) % Color.maxId)
          }
     }




     def blendAll: Map[(Color, Color), Color] = {
          var colorMap = Map[(Color, Color), Color]()

          Color.values.foreach(c1 =>
               Color.values.foreach(c2 =>
                    colorMap += ((c1, c2) -> blend(c1, c2)))
          )
          colorMap
     }
/*
     def blendAll = (
          for {
               a <- Color.values
               b <- Color.values
               c = blend(a,b)
          } yield ((a,b), c)
     ).toMap*/


     //def blendAll(a:Color, b:Color) = blender //((a,b))

     def main(args: Array[String]) {

          assert(blend(red, yellow) == orange)
          assert(blend(red, red) == red)
          assert(blend(yellow, blue) == green)
          assert(blend(purple, orange) == red)
          assert(blend(purple, brown) == brown)
          assert(blend(yellow, magenta) == purple)
          assert(blend(red, magenta) == purple)
          assert(blend(purple, white) == purple)
          assert(blend(white, red) == red)

          blendAll.foreach(t => println(s"${t._1._1} + ${t._1._2} = ${t._2}"))
     }
}
