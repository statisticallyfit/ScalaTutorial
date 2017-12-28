/*
package Loverdos_StepsInScala.chapter13_AlgebraSystem

import PrettyPrint._
import language.implicitConversions

object _Calculus {

     /*sealed*/ abstract class Expr {

          def +(other:Expr) = BinOp("+", this, other)
          def -(other:Expr) = BinOp("-", this, other)
          def *(other:Expr) = BinOp("*", this, other)
          def /(other:Expr) = BinOp("/", this, other)
          def unary_-() = UnOp("-", this)
          def unary_+() = UnOp("+", this)
     }

     // for mixing χ with numbers (χ + 2)
     implicit def IntToExpr(i: Int):Expr = Num(i)

     case object Y extends Expr { override def toString = "γ" }//γ
     case object χ extends Expr //to represent variables
     case object A extends Expr { override def toString = "α"}
     case object B extends Expr { override def toString = "β"}
     //case object Ω extends Expr
     //case object Σ extends Expr //to represent variables θ
     /*case object α extends Expr
     case object β extends Expr
     case object θ extends Expr
     case object ω extends Expr
     case object Φ extends Expr*/
     /*case object φ extends Expr
     case object Ω extends Expr*/

     case class Num(num: Int) extends Expr
     case class UnOp(operator:String, operand:Expr) extends Expr
     case class BinOp(operator:String, Left:Expr, Right:Expr) extends Expr
     case class Var(name:Expr) extends Expr
     class Function(val name:String, val arg:Expr) extends Expr { override def toString = s"$name($arg)"}
     object Function {
          def apply(n:String, a:Expr): Function = new Function(n, a)
          def unapply(f:Function): Option[(String, Expr)] = Some(f.name, f.arg)
     }


     //case object cos { def apply(arg: Expr): Expr = Function("cos", arg)}
     case class sin(override val arg: Expr) extends Function("sin", arg)
     case class cos(override val arg: Expr) extends Function("cos", arg)
     case class tan(override val arg: Expr) extends Function("tan", arg)
     case class sec(override val arg: Expr) extends Function("sec", arg)
     case class csc(override val arg: Expr) extends Function("csc", arg)
     case class cot(override val arg: Expr) extends Function("cot", arg)
     case class arcsin(override val arg: Expr) extends Function("arcsin", arg)
     case class arccos(override val arg: Expr) extends Function("arccos", arg)
     case class arctan(override val arg: Expr) extends Function("arctan", arg)
     case class arcsec(override val arg: Expr) extends Function("arcsec", arg)
     case class arccsc(override val arg: Expr) extends Function("arccsc", arg)
     case class arccot(override val arg: Expr) extends Function("arccot", arg)
     case class ln(override val arg: Expr) extends Function("ln", arg)
     case class log(base:Expr, override val arg: Expr) extends Function("log", arg)
     case class e(override val arg: Expr) extends Function("e", arg)



     def d(expr: Expr, x:Expr): Expr = expr match {
          case Num(_) => Num(0)
          case Var(y) => if(y == x) Num(1) else Num(0)
          case `χ` => if(χ == x) Num(1) else Num(0)
          case Y => if(Y == x) Num(1) else Num(0)
          case UnOp(op, u) => UnOp(op, d(u, x))
          //constant rule
          case BinOp("*", Num(n), u) => BinOp("*", n, d(u,x))
          //addition rule
          case BinOp("+", u, v) => BinOp("+", d(u,x), d(v,x))
          //product rule
          case BinOp("*", u, v) =>
               BinOp("+", BinOp("*", u, d(v,x)), BinOp("*", v, d(u,x)))
          //quotient rule
          case BinOp("/", u, v) =>
               BinOp("/", BinOp("-", BinOp("*",v,d(u,x)), BinOp("*", u, d(v,x))), BinOp("^", v, 2))
          //exponent rule: d(u^n) = nu^(n-1) * u'
          case BinOp("^", u, Num(n)) => BinOp("*", BinOp("*", n, BinOp("^", u, n-1)), d(u,x))
          //power rule d(a^u) = a^u * u' * ln(a)
          case BinOp("^", Num(a), u) => BinOp("*", BinOp("*", BinOp("^", a, u), ln(a)), d(u,x))
          //u^v = (v)(u^(v-1))(u') + (u^v)(ln u)(v')
          case BinOp("^", u, v) => {
               val right = BinOp("*", BinOp("*", v, BinOp("^", u, BinOp("-", v, 1))), d(u,x))
               val left  = BinOp("*", BinOp("*", BinOp("^", u, v), ln(u)), d(v,x))
               BinOp("+", right, left)
          }
          case BinOp(op, u, v) => BinOp(op, d(u,x), d(v,x)) // + and -
          case sin(u) => BinOp("*", cos(u), d(u,x))
          case cos(u) => BinOp("*", UnOp("-", sin(u)), d(u,x))
          case tan(u) => BinOp("*", d(u, x), BinOp("^", sec(u), 2))
          case sec(u) => BinOp("*", d(u, x), BinOp("*", sec(u), tan(u)))
          case csc(u) => UnOp("-", BinOp("*", d(u,x), BinOp("*", csc(u), cot(u))))
          case cot(u) => UnOp("-", BinOp("*", d(u,x), BinOp("^", csc(u), 2)))
          case arcsin(u) => BinOp("/", d(u,x), BinOp("^", BinOp("-", 1, BinOp("^", u, 2)), BinOp("/", 1, 2)))
          case arccos(u) => UnOp("-", d(arcsin(u), x))
          case arctan(u) => BinOp("/", d(u, x), BinOp("+", 1, BinOp("^", u, 2)))
          case arcsec(u) => //HELP how to do absolute value of u? (here before ^ binop)
               BinOp("/", d(u, x), BinOp("*", u, BinOp("^", BinOp("-",BinOp("^", u, 2), 1), BinOp("/",1, 2))))
          case arccsc(u) => UnOp("-", d(arcsec(u), x))
          case arccot(u) => UnOp("-", d(arctan(u), x))
          case log(base, u) => BinOp("/", d(u, x), BinOp("*", ln(base), u))
          case ln(u) => BinOp("*", BinOp("/", 1, u), d(u,x))
          case e(u) => BinOp("*", e(u), d(u,x))

          case _ => sys.error("Can't handle this function!")
     }


     def D(expression: Expr, variable:Expr): String ={
          prettyPrint(simplify(d(expression, variable)))
     }



     def simplify(expr: Expr): Expr = expr match {
          case `χ` => χ
          case Y => Y
          case Num(n) => Num(n)
          case Function(name, arg) => Function(name, simplify(arg))
          case UnOp("+", e) => simplify(e)
          case UnOp("-", Num(n)) => Num(-n)
          case UnOp("-", UnOp("-", e)) => simplify(e)
          case UnOp("-", e) => UnOp("-", simplify(e))
          case BinOp("+", Num(0), e) => simplify(e)
          case BinOp("+", e, Num(0)) => simplify(e)
          case BinOp("-", e, Num(0)) => simplify(e)
          case BinOp("-", Num(0), e) => UnOp("-", simplify(e))
          case BinOp("*", Num(0), _) => Num(0)
          case BinOp("*", _, Num(0)) => Num(0)
          case BinOp("*", Num(1), e) => simplify(e)
          case BinOp("*", e, Num(1)) => simplify(e)
          case BinOp("/", e, Num(1)) => simplify(e)
          case BinOp("^", e, Num(0)) => Num(1)
          case BinOp("^", e, Num(1)) => simplify(e)
          //e*n + e*m = e(n+m)
          case BinOp("+", BinOp("*", Num(n), e1), BinOp("*", Num(m), e2)) => {
               if(e1 == e2) BinOp("*", n + m, simplify(e1))
               else {
                    BinOp("+", BinOp("*", n, simplify(e1)), BinOp("*", m, simplify(e2)))
               }
          }
          // a*e1*b*e2 = a*b*e1*e2
          case BinOp("*", BinOp("*",Num(a),BinOp("^",e1,Num(n))),BinOp("*",Num(b),BinOp("^",e2,Num(m)))) =>{
               if(e1 == e2)
                    BinOp("*", a * b, BinOp("^", simplify(e1), n + m))
               else //ax^3 * cy^5 = (a*c) x^3y^5
                    BinOp("*", a * b, BinOp("*", BinOp("^", simplify(e1), n),
                         BinOp("^", simplify(e2), m)))
          }
          // a*e1*b*e2 = a*b*e1*e2
          /*case BinOp("*", BinOp("*", Num(a), e1), BinOp("*", Num(b), e2)) =>
               BinOp("*", a * b, BinOp("*", simplify(e1), simplify(e2)))*/
          //sin^2 x + cos^2 x = 1
          case BinOp("+", BinOp("^", sin(_), Num(2)), BinOp("^", cos(_), Num(2))) => Num(1)
          case BinOp("+", BinOp("^", cos(_), Num(2)), BinOp("^", sin(_), Num(2))) => Num(1)
          // tan^2 x + 1 = sec^2 x
          case BinOp("+", Num(1), BinOp("^", tan(u), Num(2))) => BinOp("^", sec(simplify(u)), 2)
          case BinOp("+", BinOp("^", tan(u), Num(2)), Num(1)) => BinOp("^", sec(simplify(u)), 2)
          // 1 + cot^2 x = csc^2 x
          case BinOp("+", Num(1), BinOp("^", cot(u), Num(2))) => BinOp("^", csc(simplify(u)), 2)
          case BinOp("+", BinOp("^", cot(u), Num(2)), Num(1)) => BinOp("^", csc(simplify(u)), 2)
          // sin(-x) = -sin x , cos, tan
          case sin(UnOp("-", u)) => UnOp("-", sin(simplify(u)))
          // cos(-x) = cos x
          case cos(UnOp("-", u)) => cos(simplify(u))
          // tan(-x) = - tan x
          case tan(UnOp("-", u)) => UnOp("-", tan(simplify(u)))
          // sec(-x) = sec x
          case sec(UnOp("-", u)) => sec(simplify(u))
          // csc(-x) = - csc x
          case csc(UnOp("-", u)) => UnOp("-", csc(simplify(u)))
          // cot(-x) = - cot x
          case cot(UnOp("-", u)) => UnOp("-", cot(simplify(u)))
          // sin x * 1 / csc x = 1
          case BinOp("*", sin(u), csc(v)) => if (u == v) Num(1) else BinOp("*", sin(simplify(u)), csc(simplify(v)))
          case BinOp("*", csc(u), sin(v)) => if (u == v) Num(1) else BinOp("*", csc(simplify(u)), sec(simplify(v)))
          // cos x * 1 / sec x = 1
          case BinOp("*", cos(u), sec(v)) => if (u == v) Num(1) else BinOp("*", cos(simplify(u)), sec(simplify(v)))
          case BinOp("*", sec(u), cos(v)) => if (u == v) Num(1) else BinOp("*", sec(simplify(u)), cos(simplify(v)))
          // tan x * 1 / cot x
          case BinOp("*", tan(u), cot(v)) => if (u == v) Num(1) else BinOp("*", tan(simplify(u)), cot(simplify(v)))
          case BinOp("*", cot(u), tan(v)) => if (u == v) Num(1) else BinOp("*", cot(simplify(u)), tan(simplify(v)))

          //sin(3x)2 = 2sin(3x)
          case BinOp("*", f@Function(_,_), Num(n)) => BinOp("*", Num(n), simplify(f))
          //-sin(3x)2 = 2sin(3x)
          case BinOp("*", UnOp("-", f@Function(_,_)), Num(n)) => BinOp("*", -n, simplify(f))
          // sin(3x) 2x = 2x sin(3x)
          case  BinOp("*", f@Function(_,_), b@BinOp(_,_,_)) => BinOp("*", simplify(b), simplify(f))
          // sin(x) x = x sin(x)
          case BinOp("*", f@Function(_,_), `χ`) => BinOp("*", χ, simplify(f))
          //(2)(3)cos(3x) = 6cos(3x)
          // 1
          case BinOp("*", Num(n), BinOp("*", Num(m), f@Function(_,_))) =>
               BinOp("*", n * m, simplify(f))
          // 2
          case BinOp("*", BinOp("*", Num(n), Num(m)), f@Function(_,_)) =>
               BinOp("*", n * m, simplify(f))
          //2cos(3x)3 = 6cos(3x)
          // 1
          case BinOp("*", Num(n), BinOp("*", f@Function(_,_), Num(m))) =>
               BinOp("*", n * m, simplify(f))
          // 2
          case BinOp("*", BinOp("*", Num(n), f@Function(_,_)), Num(m)) =>
               BinOp("*", n * m, simplify(f))
          // cos(3x)(2)(3) = 6cos(3x)
          // 1
          case BinOp("*", BinOp("*", f@Function(_,_), Num(m)), Num(n)) =>
               BinOp("*", n * m, simplify(f))
          // 2
          /*case BinOp("*", f@Function(_,_), BinOp("*", Num(m), Num(n))) =>
               BinOp("*", n * m, simplify(f))*/
          // (x^2) (3cos(5x)) = (3x^2) (cos(5x))
          case BinOp("*", BinOp("^",u,v), BinOp("*", Num(n), f@Function(_,_))) =>
               BinOp("*", simplify(BinOp("*", n, simplify(BinOp("^",u,v)))), simplify(f))
          // (x^2) (3x) = (3x^2) x
          /*case BinOp("*", BinOp("^",u,v), BinOp("*", Num(n), f@Function(_,_))) =>
               BinOp("*", BinOp("^", BinOp("*", Num(n), u), v), f)*/

          // x^2 * x^5 = x^7
          case BinOp("*", BinOp("^", e1, Num(n)), BinOp("*", e2, Num(m))) => {
               if(e1 == e2)
                    BinOp("^", simplify(e1), n + m)
               else
                    BinOp("*", BinOp("^", simplify(e1), n), BinOp("^", simplify(e2), m))
          }
          // ax^3 * x^5 = ax^8 //HELP todo hardcoded this case but how to handle in general?
          /*case BinOp("*", BinOp("*",Num(a),BinOp("^",e1,Num(n))),BinOp("*",Num(b),BinOp("^",e2,Num(m)))) =>{
               if(e1 == e2)
                    BinOp("*", a * b, BinOp("^", simplify(e1), n + m))
               else //ax^3 * cy^5 = (a*c) x^3y^5
                    BinOp("*", a * b, BinOp("*", BinOp("^", simplify(e1), n),
                         BinOp("^", simplify(e2), m)))
          }*/
          // x^5 / x^1 = x^4
          case BinOp("/", BinOp("^", e1, Num(n)), BinOp("*", e2, Num(m))) => {
               if(e1 == e2)
                    BinOp("^", simplify(e1), n - m)
               else
                    BinOp("/", BinOp("^", simplify(e1), n), BinOp("^", simplify(e2), m))
          }
          // (x^3)^8 = x^24
          case BinOp("^", BinOp("^", e, Num(n)), Num(m)) =>
               BinOp("^", simplify(e), n * m)
          // todo more log rules, more power/exp rules
          // todo polynomial simplifications (a^2 + b^2 + 2ab) = (a+b)^2
          // todo polynomial simplifications (a^2 + b^2 - 2ab) = (a-b)^2
          // todo (x + 2)(x - 2) = x^2 - 4

          // percolating constants: 4(3( (2(5x+1))^ 2)) = 48(5x+1)^2
          case BinOp("*", Num(a), BinOp("*", Num(b), rest)) =>
               BinOp("*", a * b, simplify(rest))
          case BinOp("*", Num(a), BinOp("*", rest, Num(b))) =>
               BinOp("*", a * b, simplify(rest))
          case BinOp("^", BinOp("*", Num(a), rest), Num(n)) =>
               BinOp("*", a ^ n, BinOp("^",simplify(rest), n))
          case BinOp("^", BinOp("*", rest, Num(a)), Num(n)) =>
               BinOp("*", a ^ n, BinOp("^",simplify(rest), n))
          //hardcoding function case 4sinx 3 cosx = 12 sinx cosx HELP todo how to do in general?
               //note will get handled by a*b*e1*e2 case
          /*case BinOp("*", BinOp("*", Num(a), f1@Function(_,_)), BinOp("*", Num(b), f2@Function(_,_))) =>
               BinOp("*", a * b, BinOp("*", simplify(f1), simplify(f2)))*/
          //MESSY help todo hardcoding function case 4x sinx 3cosx = 12 x sinx cosx
          //case BinOp("*", BinOp())

          case BinOp(op, left, right) => BinOp(op, simplify(left), simplify(right))
     }
}*/
