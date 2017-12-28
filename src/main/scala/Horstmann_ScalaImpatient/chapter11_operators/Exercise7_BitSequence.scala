package Horstmann_ScalaImpatient.chapter11_operators

/**
  *
  */
object Exercise7_BitSequence {

     object Utils {
          def replicate(x: Any, n: Int): String ={
               var accum: String = ""
               (1 to n) foreach { i => accum += x.toString }
               accum
          }
     }

     class BitSequence(var bits: Long = 0) {

          // note help todo what do these do? What are these weird signs?
          // see but doesnot explain |= or &=: http://www.tutorialspoint.com/java/java_bitwise_operators_examples.htm
          def apply(index: Byte): Boolean = (bits & (1 << index)) > 0
          def update(index: Byte, value: Boolean): Unit = {
               if (value)
                    bits |= (1 << index)
               else
                    bits &= ~(1 << index)
          }

          override def toString: String = {
               val spotsLeft: Int = 8 - bits.toBinaryString.length
               val binary: String = Utils.replicate(0, spotsLeft) + bits.toBinaryString
               val tuple = binary.splitAt(4)
               tuple._1 + " " + tuple._2
          }
     }

     def main(args: Array[String]) {

          val bitSeq: BitSequence = new BitSequence
          println(bitSeq(60))
     }

}
