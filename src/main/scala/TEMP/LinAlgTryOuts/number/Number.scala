package TEMP.LinAlgTryOuts


import util._
import theory._

import scala.math
import spire.math.{Rational => SMRational}

/*val ZERO: T
     val ONE: T

     def +(that: T)
     def -(that: T)
     def *(that: T)
     def /(that: T)
     def ^(): T
     def sqrt(): T

     def inverse(): T
     def opposite(): T

     def abs(): T

     def compare(that: T): Int
     def equals(that: T): Boolean*/


sealed trait Number[T] extends Ring[T] with Field[T] with Ordered[T]

abstract class Complex[T](val real: T, val imag: T) extends Number[Complex[T]]{
     def getReal = real
}
//methods inherited: +, -, opposite, / , inverse, ZERO, ONE
case class Real(override val real: Double) extends Complex[Real](Real(real), Real(0))
case class Rational(num: Int, denom: Int) extends Real(num * 1.0 / denom)
case class Natural(nat: Int) extends Rational(nat, 1) //{ require}

/*object Complex {
     implicit def apply(n: Int): Complex[Int] = new Complex(n)
}*/

object Number {

     implicit val complexNumber = new Number[Complex[Int]] {
          override val ZERO: Complex[Int] = new Complex(0,0)
          override val ONE: Complex[Int] = new Complex(1, 0)

          def +(that: Complex[Int]) = this
          def -(that: T)
          def *(that: T)
          def /(that: T)
          def ^(): T
          def sqrt(): T

          def inverse(): T
          def opposite(): T

          def abs(): T

          def compare(that: T): Int
          def equals(that: T): Boolean

     }
}



//package number
//
//import util._
//import theory._
//
//import org.apache.commons.lang3.math.Fraction
//import scala.reflect.runtime.universe._
//import scala.reflect.runtime.{universe=>ru}
//
//
//abstract class Number extends Ring[Number] with Field[Number] {
//     //methods inherited: add,subtract,opposite,divide,inverse,identity
//
//     def ZERO(): Number
//     def inverse(): Number
//     def opposite(): Number
//     def +(that: Number): Number
//     def -(that: Number): Number
//     def *(that: Number): Number
//     def *(factor: Double): Number
//     def /(that: Number): Number
//     def ^(exp: Double): Number
//     def sqrt(): Number
//     def >(that: Number): Boolean
//     def <(that: Number): Boolean
//     def >=(that: Number): Boolean
//     def <=(that: Number): Boolean
//     def !=(that: Number): Boolean = !(this == that)
//     def ==(that: Number): Boolean
//     def >(that: Double): Boolean = this.toDouble > that
//     def <(that: Double): Boolean = this.toDouble < that
//     def >=(that: Double): Boolean = this.toDouble >= that
//     def <=(that: Double): Boolean = this.toDouble <= that
//     def !=(that: Double): Boolean = this.toDouble != that
//     def ==(that: Double): Boolean = this.toDouble == that
//     def isZero: Boolean
//     def isNegative: Boolean
//     def getMultiple(that: Number): Option[Number]
//     def abs(): Number
//     def toDouble: Double
//     def toString: String
//}
//
//
///*object Number {
//
//     def ZERO[N <: Number]: N ={
//          Number.runtimeType[N] match {
//               case "Real" => Real.ZERO.asInstanceOf[N]
//               case "Rational" => Rational.ZERO.asInstanceOf[N]
//               case "Complex" => Complex.ZERO.asInstanceOf[N]
//          }
//     }
//
//     def ONE[N <: Number]: N ={
//          Number.runtimeType[N] match {
//               case "Real" => Real.ONE.asInstanceOf[N]
//               case "Rational" => Rational.ONE.asInstanceOf[N]
//               case "Complex" => Complex.ONE.asInstanceOf[N]
//          }
//     }
//
//
//     //F = from type
//     // T = to type
//     /*def toNumber2[F <: Number[F]: TypeTag, T <: Number[T]: TypeTag](toConvert: Number[F]): T ={
//          val inside = Number.internalType(toConvert)  //type of F (from)
//          val outside = Number.runtimeType[T]
//          outside match {
//               case "Real" => Real(toConvert.toDouble).asInstanceOf[T]
//               case "Rational" => Rational(toConvert.toDouble).asInstanceOf[T]
//               case "Complex" => inside match {
//                         case "Complex" => toConvert.asInstanceOf[T]
//                         case _ => Complex(toConvert.toDouble).asInstanceOf[T]
//                    }
//               case _ => throw new Exception("Type is NOT EXPECTED: one of real,rational,complex..")
//          }
//     }*/
//}*/
//
//
//
//
////------------------------------------------------------------------------------------------------------
//
//class Real(num: Double) extends Number {
//
//     def ZERO(): Real = Real.ZERO
//     def inverse(): Real = Real(1 / num)
//     def opposite(): Real = Real(-num)
//     def +(that: Number): Number = Real(this.toDouble + that.toDouble)
//     def -(that: Number): Number = Real(this.toDouble - that.toDouble)
//     def *(that: Number): Number = Real(this.toDouble * that.toDouble)
//     def *(factor: Double): Number = Real(this.toDouble * factor)
//     def /(that: Real): Number = Real(this.toDouble / that.toDouble)
//     def ^(exp: Double): Number = Real(Math.pow(this.toDouble, exp))
//     def sqrt(): Number = this ^ 0.5
//     def >(that: Number): Boolean = this.toDouble > that.toDouble
//     def <(that: Number): Boolean = this.toDouble < that.toDouble
//     def >=(that: Number): Boolean = this.toDouble >= that.toDouble
//     def <=(that: Number): Boolean = this.toDouble <= that.toDouble
//     def ==(that: Number): Boolean = this.toDouble == that.toDouble
//     /*def ==(that: Double): Boolean = this.toDouble == that
//     def !=(that: Double): Boolean = this.toDouble != that*/
//     def isZero: Boolean = this.toDouble == 0
//     def isNegative: Boolean = this.toDouble < 0
//     def getMultiple(that: Number): Option[Number] = that.isZero match {
//          case true => None
//          case false => Some(this / that)
//     }
//     def abs(): Real = Real(Math.abs(this.toDouble))
//
//     def toDouble: Double = num
//     def toRational: Rational = Rational(num)
//     def toComplex: Complex = Complex(num)
//     def toFrac: Fraction = Fraction.getFraction(num )
//
//     override def toString: String = {
//          toDouble.round - toDouble == 0 match {
//               case true => toDouble.round.toInt.toString
//               //round to 4 decimal places anyway
//               case false => Util.GenOps.roundTo(toDouble, 4).toString
//          }
//     }
//}
//
//object Real {
//     val ZERO = Real(0)
//     val ONE = Real(1)
//
//     def apply(): Real = new Real(0)
//     def apply(double: Double): Real = new Real(double)
//     def apply[N <: Number](number: Number): Real = new Real(number.toDouble)
//}
//
//
//
////------------------------------------------------------------------------------------------------------
//
//class Rational(num:Int, denom:Int) extends Number {
//
//     private val numDouble = num * 1.0
//     private val denomDouble = denom * 1.0
//     private val frac: Fraction = Fraction.getFraction(num, denom).reduce()
//     var numerator = frac.getNumerator
//     var denominator = frac.getDenominator
//     val double = numDouble / denomDouble
//
//     def ZERO(): Rational = Rational.ZERO
//     def inverse(): Rational = Rational(denom, num)
//     def opposite(): Rational = Rational(-num, denom)
//
//     def +(that: Number): Number = Rational(this.double + that.toDouble)
//     def -(that: Number): Number = Rational(this.double - that.toDouble)
//     def *(that: Number): Number = Rational(this.double * that.toDouble)
//     def *(factor: Double): Number = Rational(this.double * factor)
//     def /(that: Number): Number = Rational(this.double / that.toDouble)
//     def ^(exp: Double): Number = Rational(Math.pow(this.double, exp))
//     def sqrt(): Number = this ^ 0.5
//     def >(that: Number): Boolean = this.double > that.toDouble
//     def <(that: Number): Boolean = this.double < that.toDouble
//     def >=(that: Number): Boolean = this.double >= that.toDouble
//     def <=(that: Number): Boolean = this.double <= that.toDouble
//     def ==(that: Number): Boolean = this.double == that.toDouble
//     /*def ==(that: Double): Boolean = this == Rational(that)
//     def !=(that: Double): Boolean = this != Rational(that)*/
//     def isZero: Boolean = this.numDouble == 0
//     def isNegative: Boolean = this.numDouble < 0 && this.denomDouble >= 0 ||
//          this.numDouble >= 0 && this.denomDouble < 0
//     def getMultiple(that: Number): Option[Number] = that.isZero match {
//          case true => None
//          case false => Some(this / that)
//     }
//     def abs(): Rational = Rational(Math.abs(numDouble).toInt, Math.abs(denomDouble).toInt)
//     def toDouble: Double = numDouble / denomDouble
//     //def toFrac: Fraction = frac
//     //def toNumber: Number[Rational] = this.asInstanceOf[Number[Rational]]
//     def toReal: Real = Real(double)
//     //def toRational: Rational = this
//     def toComplex: Complex = Complex(double)
//
//     override def toString: String = denomDouble match {
//          case 1 => numerator.toString
//          case _ => numerator + "/" + denominator
//     }
//}
//
//object Rational {
//     val ZERO = Rational(0)
//     val ONE = Rational(1)
//
//     def apply(): Rational = new Rational(0, 1)
//     def apply(num:Int, denom:Int): Rational = new Rational(num, denom)
//     def apply[N <: Number](number: Number): Rational = Rational(number.toDouble)
//     //def apply(num: Double, denom: Double): Rational = Rational(num) / Rational(denom)
//     def apply(double: Double): Rational = {
//          val f: Fraction = Fraction.getFraction(double).reduce()
//          new Rational(f.getNumerator, f.getDenominator)
//     }
//}
//
//
////------------------------------------------------------------------------------------------------------
//
//class Complex[T](val real:Real, val imaginary:Real) extends Number {
//
//     private val modulus: Double = Math.sqrt(Math.pow(real.toDouble, 2) + Math.pow(imaginary.toDouble, 2))
//
//     def ZERO(): Complex = Complex.ZERO
//     def inverse(): Complex = Complex(real/(real*real + imaginary*imaginary),
//          imaginary.opposite()/(real*real + imaginary*imaginary))
//     def opposite(): Complex = new Complex(this.real.opposite(), this.imaginary.opposite())
//     def conjugate(): Complex = Complex(real, imaginary.opposite())
//     def +(that: Number): Number = Complex(real + that.real, imaginary + that.imaginary)
//     def -(that: Number): Number = Complex(real - that.real, imaginary - that.imaginary)
//     def *(that: Number): Number = Complex(real*that.real - imaginary*that.imaginary,
//          real*that.imaginary + imaginary*that.real)
//     def *(factor: Double): Number = Complex(real * factor, imaginary * factor)
//     def /(that: Number): T ={
//          // this is more numerically stable than the concise formulation
//          if (that.isZero) throw new ArithmeticException
//
//          val quot: Complex = this * that.conjugate()
//          val recMod: Real = (that.real ^ 2) + (that.imaginary ^ 2)
//          new Complex(quot.real/recMod, quot.imaginary/recMod)
//     }
//     def square(): Complex = Complex((real^2) - (imaginary^2), real * imaginary * 2)
//     def ^(exp: Double): Number = Complex() // todo and say if that == 1/2 then use sqrt() method
//     def sqrt(): Number = Complex() // todo https://www.google.ro/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=sqrt%20of%20complex%20number
//     def >(that: Number): Boolean = this.modulus > that.modulus
//     def <(that: Number): Boolean = this.modulus < that.modulus
//     def >=(that: Number): Boolean = this.modulus >= that.modulus
//     def <=(that: Number): Boolean = this.modulus <= that.modulus
//     def ==(that: Number): Boolean = this.real == that.real && this.imaginary == that.imaginary
//     /*def ==(that: Double): Boolean = this == Complex(that)
//     def !=(that: Double): Boolean = this != Complex(that)*/
//     def isZero: Boolean = this.real.isZero && this.imaginary.isZero
//     def isNegative: Boolean = this.real.isNegative && (this.imaginary.isZero || this.imaginary.isNegative)
//     def getMultiple(that: Number): Option[Complex] = that.isZero match {
//          case true => None
//          case false => Some(this / that)
//     }
//     def theta(): Angle = new Angle(Math.atan(imaginary.toDouble / real.toDouble))
//     def abs(): Complex = Complex(modulus)
//
//     def toDouble: Double = if(imaginary.isZero && real.isNegative) -modulus else modulus //todo is this correct?
//     //def toNumber: Number[Complex] = this.asInstanceOf[Number[Complex]]
//     def toReal: Real = Real(modulus)
//     def toRational: Rational = Rational(modulus)
//     //def toComplex: Complex = this
//     def toFrac: Fraction = Fraction.getFraction(modulus)
//     //def imaginaryIsZero: Boolean = imaginary == Rational.ZERO
//
//
//     def displayWithReals(): String ={
//          if(real.isZero && imaginary.isZero) return "0"
//          if(imaginary.isZero) return real.toString
//
//          var imagStr: String = ""
//          var realStr: String = ""
//
//          if(real.isZero){
//               //dealing with imag now
//               imagStr = if(imaginary == -1) "-i"
//               else if(imaginary == 1) "i"
//               else if(imaginary.isNegative) imaginary + "i"
//               else imaginary + "i"
//          } else {
//               realStr = real.toString
//               imagStr = if(imaginary == -1) " - i"
//               else if(imaginary == 1) " + i"
//               else if(imaginary.isNegative) " - " + imaginary.abs() + "i"
//               else " + " + imaginary + "i"
//          }
//
//          realStr + imagStr
//     }
//
//     override def toString: String = {
//
//          val realTemp: Rational = Rational(real)
//          val imagTemp: Rational = Rational(imaginary)
//
//          if(realTemp.isZero && imagTemp.isZero) return "0"
//          if(imagTemp.isZero) return realTemp.toString
//
//          var imagStr: String = ""
//          var realStr: String = ""
//
//          if(realTemp.isZero){
//               //dealing with imag now
//               imagStr = if(imagTemp == -1) "-i"
//               else if(imagTemp == 1) "i"
//               else if(imagTemp.isNegative) imagTemp + "i"
//               else imagTemp + "i"
//          } else {
//               realStr = realTemp.toString
//               imagStr = if(imagTemp == -1) " - i"
//               else if(imagTemp == 1) " + i"
//               else if(imagTemp.isNegative) " - " + imagTemp.abs() + "i"
//               else " + " + imagTemp + "i"
//          }
//
//          realStr + imagStr
//     }
//}
//
//object Complex {
//     val ZERO = Complex() // for use inside class
//     val ONE = Complex(1, 0)
//
//     def apply(): Complex = new Complex(Real.ZERO, Real.ZERO)
//     def apply(num: Double): Complex = new Complex(Real(num), Real.ZERO)
//     def apply(a: Double, b: Double): Complex = new Complex(Real(a), Real(b))
//     def apply(a: Real, b: Real): Complex  = Complex(a.toDouble, b.toDouble)
//     def apply(a: Real, b: Double) = new Complex(a, Real(b))
//     def apply(a: Double, b: Real) = new Complex(Real(a), b)
//     def apply(a: Rational, b: Rational): Complex  = new Complex(a.toReal, b.toReal)
//     def apply(a: Complex, b: Complex): Complex  = new Complex(a.real + b.real, a.imaginary + b.imaginary)
//     def apply(a: Complex, b: Rational): Complex  = new Complex(a.real + b.toReal, a.imaginary)
//     def apply(a: Rational, b: Complex): Complex  = new Complex(a.toReal + b.real, b.imaginary)
//     def apply(a: Complex, b: Real): Complex  = new Complex(a.real + b, a.imaginary)
//     def apply(a: Real, b: Complex): Complex  = new Complex(a + b.real, b.imaginary)
//     def apply(a: Real, b: Rational): Complex  = new Complex(a, b.toReal)
//     def apply(a: Rational, b: Real): Complex  = new Complex(a.toReal, b)
//}
//
