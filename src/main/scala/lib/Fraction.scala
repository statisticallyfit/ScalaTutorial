package lib


object Utils {
     def GCD(num1: Int, num2: Int): Int ={
          def innerGCD(a: Int, b:Int): Int ={
               if(b == 0) a
               else GCD(b, a % b)
          }
          innerGCD(Math.abs(num1), Math.abs(num2))
     }

     def LCM(num1: Int, num2: Int): Int = {
          var a = Math.abs(num1)
          var b = Math.abs(num2)
          val c = a * b

          while (a != b) {
               if (a > b) a -= b
               else       b -= a
          }
          Math.abs(c / a)
     }

     def toCommonDenominator(f1:Fraction, f2:Fraction): (Int,Int,Int) ={
          val lcm: Int = Utils.LCM(f1.denom, f2.denom)
          val leftMult: Int = lcm / f1.denom
          val rightMult: Int = lcm / f2.denom
          val thisNumer: Int = f1.numer * leftMult
          val thatNumer: Int = f2.numer * rightMult
          (thisNumer, thatNumer, lcm)
     }
}

case class Fraction(private val n: Int, private val d: Int) {

     require(d != 0)
     private val g = Utils.GCD(n.abs, d.abs)
     var numer = n / g
     var denom = d / g


     //note if we have (4/(-5)) then make (-4/5)
     //else if we have (4/5) keep as (4/5)
     private def handleSigns(): Unit = {
          //handle negative signs
          if(this.numer * this.denom < 0) this.numer = -Math.abs(this.numer)
          else this.numer = Math.abs(this.numer)
          this.denom = Math.abs(this.denom)
     }

     def simplify(): Fraction = {
          this.handleSigns()
          val gcd: Int = Utils.GCD(this.numer, this.denom)
          Fraction(this.numer / gcd, this.denom / gcd)
     }

     def +(that: Fraction): Fraction = {
          this.handleSigns()
          that.handleSigns()
          val (n1, n2, d) = Utils.toCommonDenominator(this, that)
          Fraction(n1 + n2, d).simplify()
     }

     def -(that: Fraction): Fraction = {
          this.handleSigns()
          that.handleSigns()
          val (n1, n2, d) = Utils.toCommonDenominator(this, that)
          Fraction(n1 - n2, d).simplify()
     }

     def *(that: Fraction) = Fraction(this.numer * that.numer, this.denom * that.denom).simplify()

     def /(that: Fraction) = Fraction(this.numer * that.denom, this.denom * that.numer).simplify()

     def ==(that: Fraction): Boolean = {
          val f1 = this.simplify()
          val f2 = that.simplify()
          f1.numer == f2.numer && f1.denom == f2.denom
     }

     override def toString: String = {
          if(this.denom != 1) this.numer.toString + " / " + this.denom.toString
          else this.numer.toString
     }
}
