package Books.Loverdos_StepsInScala.chapter13_AlgebraSystem

import PrettyPrint._
import language.implicitConversions
import Simplify._
import Books.lib.Fraction
import Declarations._


object Derivative {


     def d(expr: Expression)(x:Expression): Expression = {
          val derivative = expr match {
               case Num(_) => Num(0)
               case y:Variable => if(y == x) Num(1) else Num(0)
               case X => if(X == x) Num(1) else Num(0)
               case Y => if(Y == x) Num(1) else Num(0)
               case Neg(u) => Neg(d(u)(x))
               //constant rule
               case Mul(Num(n), u) => n * clean(d(u)(x))
               //addition rule
               case Add(u, v) => clean(d(u)(x)) + clean(d(v)(x))
               //product rule
               case Mul(u, v) => clean(d(u)(x)) * v + clean(d(v)(x)) * u
               //quotient rule
               case Div(u, v) => (v * clean(d(u)(x)) - u * clean(d(v)(x))) / v^2
               //power rule d(a^u) = a^u * u' * ln(a)
               case Pow(Num(a), u) => a^u * clean(d(u)(x)) * ln(a)
               //exponent rule: d(u^n) = nu^(n-1) * u'
               case Pow(u, Num(n)) => n*u^(n-1) * clean(d(u)(x))
               //u^v = (v)(u^(v-1))(u') + (u^v)(ln u)(v')
               case Pow(u, v) => v * u^(v-1) * clean(d(u)(x)) + u^v * ln(u) * clean(d(v)(x))
               //trig functions
               case sin(u) => clean(d(u)(x)) * cos(u)
               case cos(u) => -clean(d(u)(x)) * sin(u)
               case tan(u) => clean(d(u)(x)) * sec(u)^2
               case sec(u) => clean(d(u)(x)) * sec(u) * tan(u)
               case csc(u) => -clean(d(u)(x)) * csc(u) * cot(u)
               case cot(u) => -clean(d(u)(x)) * csc(u)^2
               case arcsin(u) => clean(d(u)(x)) / ((1 - u^2)^(1/2))
               case arccos(u) => -clean(d(arcsin(u))(x))
               case arctan(u) => clean(d(u)(x)) / (1 + u^2)
               case arcsec(u) => clean(d(u)(x)) / (absExpr(u) * (u^2 - 1) ^ Fraction(1,2))
               case arccsc(u) => -clean(d(arcsec(u))(x))
               case arccot(u) => -clean(d(arctan(u))(x))
               case log(base, u) => clean(d(u)(x)) / (ln(base) * u)
               case ln(u) => clean(d(u)(x)) / u
               case e(u) => clean(d(u)(x)) * e(u)

               case _ => sys.error("Can't handle this function!")
          }
          clean(derivative)
     }

     def absExpr(expr:Expression):Expression = expr match {
          case Neg(e) => e
          case other@_ => other
     }


     def D(expression: Expression, inTermsOfVar: Expression): String ={
          prettyPrint(d(expression)(inTermsOfVar))
     }





     /*def makeTree(expr: Expression): Tree = expr match {
          case Num(n) => Leaf(n)
          case v:Variable => Leaf(v)
          case Neg(e) => Node("-", Empty, makeTree(e))
          case f1@Function1(_,_) => Leaf(f1)
          case f2@Function2(_,_,_) => Leaf(f2)
          case Add(u, v) => Node("+", makeTree(u), makeTree(v))
          case Sub(u, v) => Node("-", makeTree(u), makeTree(v))
          case Mul(u, v) => Node("*", makeTree(u), makeTree(v))
          case Div(u, v) => Node("/", makeTree(u), makeTree(v))
          case Pow(u, v) => Node("^", makeTree(u), makeTree(v))
     }


     def simplifyTree(tree:Tree): Tree = tree match {
          case Empty => Empty
          case Leaf(any) => Leaf(any)
          //case Node("*", Num(n), Node("*", Num(m), u)) =>  Node("*", n*m, simplifyTree(u))
          case Node(op, Leaf(u), v) => Node(op, Leaf(u), simplifyTree(v))
          //case

          case Node(op, left, right) => Node(op, simplifyTree(left), simplifyTree(right))

     }*/
}