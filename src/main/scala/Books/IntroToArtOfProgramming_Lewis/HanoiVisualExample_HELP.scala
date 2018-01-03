package Books.IntroToArtOfProgramming_Lewis

//HELP cannot import scala swing
//import scala.swing._

object HanoiVisualExample_HELP {


     class Hanoi(pegs: Array[List[Int]]) {

          def getPegName(pegInt:Int): String ={
               pegInt match {
                    case 0 => "A"
                    case 1 => "B"
                    case 2 => "C"
               }
          }

          def moveDisk(from:Int, to:Int):Unit ={
               //necessary that destination is Empty OR that disk we're moving
               // is smaller than disk we're putting it on.
               require(pegs(to).isEmpty || pegs(from).head < pegs(to).head)
               val diskName = pegs(from).head
               Console.println(s"Move disk $diskName from ${getPegName(from)} to ${getPegName(to)}")
               pegs(to) = diskName :: pegs(to) //put first disk on 'from' peg into 'to' peg
               pegs(from) = pegs(from).tail //the remainder should be left on 'from' peg
          }

          def moveNDisks(n:Int, from:Int, to:Int): Unit ={
               val temp = 3 - from - to
               if(n == 1) {
                    moveDisk(from, to)
               } else {
                    moveNDisks(n-1, from, temp)
                    moveDisk(from, to)
                    moveNDisks(n-1, temp, to)
               }
          }

          def solve = moveNDisks(pegs(0).size, 0, 2)
     }
}
