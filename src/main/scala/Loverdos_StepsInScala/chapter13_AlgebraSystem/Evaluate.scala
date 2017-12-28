package Loverdos_StepsInScala.chapter13_AlgebraSystem

import Declarations._

/**
  *
  */
object Evaluate {
     def eval(expr: Expression, value: Double): Double = expr match {
          case Num(n) => n
          case _:Variable => value
          case Neg(e) => -eval(e, value)
          case Add(x, y) => eval(x, value) + eval(y, value)
          case Sub(x, y) => eval(x, value) - eval(y, value)
          case Mul(x, y) => eval(x, value) * eval(y, value)
          case Div(x, y) => eval(x, value) / eval(y, value)
          case Pow(x, y) => Math.pow(eval(x, value), eval(y, value))
          case sin(x) => Math.sin(eval(x, value))
          case cos(x) => Math.cos(eval(x, value))
          case tan(x) => Math.tan(eval(x, value))
          case sec(x) => 1 / eval(cos(x), value)
          case csc(x) => 1 / eval(sin(x), value)
          case cot(x) => 1 / eval(tan(x), value)
          case arcsin(x) => Math.asin(eval(x, value))
          case arccos(x) => Math.acos(eval(x, value))
          case arctan(x) => Math.atan(eval(x, value))
          case arccsc(x) => 1 / eval(arcsin(x), value)
          case arcsec(x) => 1 / eval(arccos(x), value)
          case arccot(x) => 1 / eval(arctan(x), value)
          case log(base, x) => Math.log10(eval(x, value)) / Math.log10(eval(base, value))
          case ln(x) => Math.log(eval(x, value))
     }
}
