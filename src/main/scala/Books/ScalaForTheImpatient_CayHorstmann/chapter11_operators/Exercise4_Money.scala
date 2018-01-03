package Books.ScalaForTheImpatient_CayHorstmann.chapter11_operators

/**
  *
  */
object Exercise4_Money {

     object Utils {
          def normalize(dollars: Int, cents: Int): (Int, Int) = {
               (cents / 100 + dollars, cents % 100)
          }
     }

     class Money(var dollars: Int, var cents: Int) {

          val ds = Math.abs(dollars); val cs = Math.abs(cents)
          val tup = Utils.normalize(ds, cs)
          dollars = tup._1
          cents = tup._2

          // note normalize so that 100 cents == 1 dollar
          private def normalize(): Unit = {
               dollars = cents / 100 + dollars
               cents = cents % 100
          }

          // note normalize so that 100 cents == 1 dollar AND return new object
          private def normalizeMoney(): Money = {
               Money(cents / 100 + dollars, cents % 100)
          }

          // note add
          def +(that: Money): Money = {
               this.normalize()
               that.normalize()
               Money(this.dollars + that.dollars, this.cents + that.cents).normalizeMoney()
          }

          // note subtract
          def -(that: Money): Money = {
               this.normalize()
               that.normalize()

               val cents1 = this.dollars * 100 + this.cents
               val cents2 = that.dollars * 100 + that.cents
               val diff = Math.abs(cents1 - cents2)

               Money(0, diff).normalizeMoney()
          }

          // note equal
          def ==(that: Money): Boolean = {
               this.normalize()
               that.normalize()
               this.dollars == that.dollars && this.cents == that.cents
          }

          override def toString: String = {
               var total: String = "$ " + this.dollars.toString + "."
               val centsStr: String = this.cents.toString

               if(centsStr.length < 2) total += "0" + centsStr
               else total += centsStr

               total
          }
     }

     object Money {
          def apply(dollars: Int, cents: Int): Money = new Money(dollars, cents)
          def unapply(m: Money): Option[(Int, Int)] = Some(m.dollars, m.cents)
     }


     def main(args: Array[String]) {

          val m1 = Money(32, 105)
          println(m1)
          assert(m1.dollars == 33 && m1.cents == 5)

          val m2 = Money(-2, -900)
          println(m2)
          assert(m2.dollars == 11 && m2.cents == 0)

          println(Money(33, 11))

          assert(Money(-2, -900) + Money(32, -105) == Money(44, 5))
          assert(Money(-2, -900) - Money(32, -105) == Money(22, 5))
          assert(Money(1, 75) + Money(0, 50) == Money(2, 25))
     }

}
