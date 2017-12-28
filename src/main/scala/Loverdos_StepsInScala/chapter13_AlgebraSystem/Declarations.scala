package Loverdos_StepsInScala.chapter13_AlgebraSystem

import scala.language.implicitConversions
import lib.Fraction

/**
  *
  */
object Declarations {


     abstract class Tree
     case object Empty extends Tree
     case class Node(op:String, left:Tree, right:Tree) extends Tree
     case class Leaf(element: Expression) extends Tree //can hold X, 4 , or cos(x) ... etc

     abstract class Expression/* extends Tree*/ {
          def +(other:Expression) = Add(this, other)
          def -(other:Expression) = Sub(this, other)
          def *(other:Expression) = Mul(this, other)
          def /(other:Expression) = Div(this, other)
          def ^(other:Expression) = Pow(this, other)
          def unary_-() = Neg(this)
          def unary_+() = UnOp("+", this)
     }
     abstract class Variable extends Expression

     // for mixing χ with numbers (χ + 2)
     implicit def IntToExpr(i: Int):Expression = Num(i)
     //implicit def DoubleToExpr(d: Double):Expression = Num(d)
     implicit def VariableToVar(variable: Variable): Expression = Var(variable)
     implicit def FractionToExpr(frac: Fraction): Expression = Frac(frac)

     case object Y extends Variable { override def toString = "γ" }//γ
     case object X extends Variable { override def toString = "χ" }
     case object A extends Variable { override def toString = "α" }
     case object B extends Variable { override def toString = "β" }
     case object θ extends Variable { override def toString = "θ" }


     case class Num(num: Int) extends Expression
     case class Frac(frac: Fraction) extends Expression
     case class Var(name:Variable) extends Expression //can only hold variables (X, alpha, beta, theta)
     case class Neg(operand:Expression) extends Expression
     case class UnOp(op:String, oper:Expression) extends Expression
     //case class Sqrt(arg: Expression) extends Expression
     case class Add(left: Expression, right: Expression) extends Expression
     case class Sub(left: Expression, right: Expression) extends Expression
     case class Mul(left: Expression, right: Expression) extends Expression
     case class Div(left: Expression, right: Expression) extends Expression
     case class Pow(left: Expression, right: Expression) extends Expression
     class Func1(val name:String, val arg:Expression)
          extends Expression { override def toString = s"$name($arg)"}
     class Func2(val name:String, val arg1:Expression, val arg2:Expression)
          extends Expression { override def toString = s"$name($arg1, $arg2)" }
     object Func1 {
          def apply(n:String, a:Expression): Func1 = new Func1(n, a)
          def unapply(f:Func1): Option[(String, Expression)] = Some(f.name, f.arg)
     }
     object Func2 {
          def apply(name:String, arg1:Expression, arg2:Expression): Func2 = new Func2(name, arg1, arg2)
          def unapply(f:Func2): Option[(String, Expression, Expression)] = Some(f.name, f.arg1, f.arg2)
     }


     //case object cos { def apply(arg: Expr): Expr = Function("cos", arg)}
     case class sin(override val arg: Expression) extends Func1("sin", arg)
     case class cos(override val arg: Expression) extends Func1("cos", arg)
     case class tan(override val arg: Expression) extends Func1("tan", arg)
     case class sec(override val arg: Expression) extends Func1("sec", arg)
     case class csc(override val arg: Expression) extends Func1("csc", arg)
     case class cot(override val arg: Expression) extends Func1("cot", arg)
     case class arcsin(override val arg: Expression) extends Func1("arcsin", arg)
     case class arccos(override val arg: Expression) extends Func1("arccos", arg)
     case class arctan(override val arg: Expression) extends Func1("arctan", arg)
     case class arcsec(override val arg: Expression) extends Func1("arcsec", arg)
     case class arccsc(override val arg: Expression) extends Func1("arccsc", arg)
     case class arccot(override val arg: Expression) extends Func1("arccot", arg)
     case class ln(override val arg: Expression) extends Func1("ln", arg)
     case class log(base:Expression, arg: Expression) extends Func2("log", base, arg)
     case class e(override val arg: Expression) extends Func1("e", arg)
     case class sqrt(override val arg: Expression) extends Func1("sqrt", arg)
}
