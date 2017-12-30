package Books.Horstmann_ScalaImpatient.chapter6_objects


/**
  *
  */
object Exercise6_7_Suit {

     object Suit extends Enumeration {
          type Suit = Value // todo: why if erase this, method isRed doesn't work?

          val Spade = Value("♠")
          val Club = Value("♣")
          val Heart = Value("♥")
          val Diamond = Value("♦")
          def isRed(card: Suit) = card == Heart || card == Diamond
     }

     def main(args: Array[String]) {
          println(Suit.Club)
          println(Suit.Heart)
          println(Suit.values)
          println(for (s <- Suit.values) yield (s.id, s, Suit.isRed(s)))
     }
}
