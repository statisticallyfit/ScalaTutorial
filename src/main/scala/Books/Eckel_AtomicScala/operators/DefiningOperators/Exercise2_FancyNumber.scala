package Books.Eckel_AtomicScala.operators.DefiningOperators

/**
  *
  */
object Exercise2_FancyNumber {

     class FancyNumber(base: Int){

          def power(p: Int) = scala.math.pow(base.toDouble, p.toDouble)
          def ^(p: Int) = power(p)
     }

     def main(args: Array[String]) {
          val f1 = new FancyNumber(2)
          assert(f1.power(3) == 8)
          assert((f1 ^ 3) == 8)

          val f2 = new FancyNumber(10)
          assert(f2.power(2) == 100)
          assert((f2 ^ 2) == 100)
     }
}
