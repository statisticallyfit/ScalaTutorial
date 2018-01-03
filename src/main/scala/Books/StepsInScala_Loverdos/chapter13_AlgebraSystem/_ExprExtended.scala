/*
package Loverdos_StepsInScala.chapter13_AlgebraSystem


import language.implicitConversions

object ExprExtended {

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

     case object χ extends Expr //to represent variables
     case class Num(num: Int) extends Expr
     case class UnOp(operator:String, operand:Expr) extends Expr
     case class BinOp(operator:String, Left:Expr, Right:Expr) extends Expr
     class Function(val name:String, val arg:Expr) extends Expr
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
     case class log(override val arg: Expr) extends Function("log", arg)
     case class e(override val arg: Expr) extends Function("e", arg)



     def D(expr: Expr): Expr = expr match {
          case Num(_) => Num(0)
          case `χ` => Num(1)
          case UnOp(op, u) => UnOp(op, D(u))
          //constant rule
          case BinOp("*", Num(n), u) => BinOp("*", n, D(u))
          //addition rule
          case BinOp("+", u, v) => BinOp("+", D(u), D(v))
          //product rule
          case BinOp("*", u, v) =>
               BinOp("+", BinOp("*", u, D(v)), BinOp("*", v, D(u)))
          //quotient rule
          case BinOp("/", u, v) => {
               val top = BinOp("-", BinOp("*",v,D(u)), BinOp("*", u, D(v)))
               val bottom = BinOp("^", v, 2)
               BinOp("/", top, bottom)
          }
          //exponent rule: d(u^n) = nu^(n-1) * u'
          case BinOp("^", u, Num(n)) => {
               val left = BinOp("*", n, BinOp("^", u, n-1))
               BinOp("*", left, D(u))
          }
          //power rule d(a^u) = a^u * u' * ln(a)
          case BinOp("^", Num(a), u) =>
               BinOp("*", BinOp("*", BinOp("^", a, u), ln(a)), D(u))
          //u^v = (v)(u^(v-1))(u') + (u^v)(ln u)(v')
          case BinOp("^", u, v) => {
               val right = BinOp("*", BinOp("*", v, BinOp("^", u, BinOp("-", v, 1))), D(u))
               val left  = BinOp("*", BinOp("*", BinOp("^", u, v), ln(u)), D(v))
               BinOp("+", right, left)
          }
          case BinOp(op, u, v) => BinOp(op, D(u), D(v)) // + and -
          case sin(u) => BinOp("*", cos(u), D(u))
          case cos(u) => BinOp("*", UnOp("-", sin(u)), D(u))
          case ln(u) => BinOp("*", BinOp("/", 1, u), D(u))
          case e(u) => BinOp("*", e(u), D(u))
          case arcsin(u) => BinOp("/", D(u), BinOp("^", BinOp("-", 1, BinOp("^", u, 2)), BinOp("/", 1, 2)))
          case arccos(u) => UnOp("-",
               BinOp("/", D(u), BinOp("^", BinOp("-", 1, BinOp("^", u, 2)), BinOp("/", 1, 2))))
          //case _ => sys.error("Can't handle this function!")
     }




     def simplify(expr: Expr): Expr = expr match {
          case `χ` => χ
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
          // todo tan^2 x + 1 = sec^2 x
          // todo http://www.purplemath.com/modules/idents.htm

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


     // ------------------ Showing suite ------------------------------

     def prettyPrint(expr: Expr):String = expr match {
          case `χ` => "χ"
          case Num(n) => n.toString
          case UnOp(op, e) => combineUnary(op, e)
          //op + "(" + prettyPrint(e) + ")"
          case BinOp(op, a, b) => combineBinary(op, a, b)
          //"(" + prettyPrint(a) + ")" + op + "(" + prettyPrint(b) + ")"
          case Function(name, e) =>
               name + "(" + prettyPrint(e) + ")"
     }

     //note if precedence of an expr < opPrec then enclose expr in ( )
     def combineBinary(op:String, a:Expr, b:Expr) ={
          val aPrec = precedence(a)
          val bPrec = precedence(b)
          val opPrec = binPrec(op)
          var aStr = prettyPrint(a)
          var bStr = prettyPrint(b)
          if(aPrec < opPrec)
               aStr = s"($aStr)"
          if(bPrec < opPrec)
               bStr = s"($bStr)"

          //to print just like math expression
          if(op == "*") {
               a match {
                    case BinOp("^", _,_) => s"($aStr)$bStr"
                    case Num(n) => b match {
                         case BinOp("^",_,_) => s"$aStr($bStr)" // 12x^5  and   7(χ^2)sin(χ)
                         case _ => s"$aStr$bStr" //if a = 4 and b = 3 then result is 43 (not correct)
                    }
                    case _ => b match {
                         case BinOp("^",_,_) => s"($aStr)($bStr)"
                         case _ => s"$aStr$bStr"
                    }
               }
          }
          else if(op == "^") s"$aStr^$bStr"
          else s"$aStr $op $bStr"
     }

     def combineUnary(op:String, expr:Expr) ={
          val eStr = prettyPrint(expr)
          val subExpr =
               if(precedence(expr) < unPrec)
                    "(" + eStr + ")"
               else eStr

          op + subExpr
     }

     //the higher the precedence, the more important it is
     def precedence(expr: Expr) = expr match {
          case BinOp(op,_,_) => binPrec(op)
          case _ => unPrec
     }

     def binPrec(op: String) = op match {
          case "+" => 1
          case "-" => 1
          case "*" => 2
          case "/" => 2
          case "^" => 3
          case _ => sys.error("Unknow binary operator: " + op)
     }

     def unPrec = 4





     // ------------------

     def main(args: Array[String]) {

          val d = D(BinOp("^", χ, 2))
          val s = simplify(d)
          println(prettyPrint(s))
          assert(prettyPrint(s) == "2χ")

          // 3 cos(3x) 2 = 6 cos(3x)
          val e1 = BinOp("*", 3, BinOp("*", 2, cos(BinOp("*",5,χ))))
          val pe1 = prettyPrint(simplify(e1))
          println("1)  " + pe1)
          assert(pe1 == "6cos(5χ)")

          // sin(3x) 2 = 2sin(3x)
          val e2 = BinOp("*", sin(BinOp("*", 3, χ)), 2)
          val pe2 = prettyPrint(simplify(e2))
          println("2)  " + pe2)
          assert(pe2 == "2sin(3χ)")

          // -sin(3x) 2 = -2sin(3x)
          val e3 =  BinOp("*", UnOp("-", sin(BinOp("*", 3, χ))), 2)
          val pe3 = prettyPrint(simplify(e3))
          println("3)  " + pe3)
          assert(prettyPrint(simplify(e3)) == "-2sin(3χ)")

          // -sin(3x) (-2) = 2sin(3x)
          val e4 = BinOp("*", UnOp("-", sin(BinOp("*", 3, χ))), -2)
          val s4 = simplify(e4)
          val pe4 = prettyPrint(s4)
          println("4)  " + pe4)
          assert(pe4 == "2sin(3χ)")

          // sin(x) 4x = 4x sin(x)
          val e5 = BinOp("*", sin(χ), BinOp("*",4,χ))
          val s5 = simplify(e5)
          val pe5 = prettyPrint(s5)
          println("5)  " + pe5)
          assert(pe5 == "4χsin(χ)")

          // case 1 - constants are percolated to the front
          // (χ^2)(3cos(3χ))) = 3(x^2)cos(3x)
          //HELP not working for real life
          val e6 = BinOp("*", BinOp("^",χ,2), BinOp("*", 3, cos(BinOp("*",5,χ))))
          val pe6 = prettyPrint(simplify(e6))
          println("6)  " + pe6)
          assert(pe6 == "3(χ^2)cos(5χ)")

          // case 2 - constants are percolated to the front
          //(3sin(x))(4cos(x)) = 12sin(x)cos(x)
          // HELP todo
          val e7 = BinOp("*", BinOp("*",3, sin(χ)), BinOp("*",4, cos(χ)))
          val s7 = simplify(e7)
          val pe7 = prettyPrint(s7)
          println("7)  " + pe7)
          //assert(pe7 == "12sin(χ)cos(χ)")

          //case 3 - constants percolated to front
          // 3x^2 * 4y^3 = 12 (x^2)(y^3)
          // 3e1 * 4e2 = 12 e1 e2
          // HELP
          val e8 = BinOp("*", BinOp("*",3,BinOp("^",χ,2)), BinOp("*",4,BinOp("^",χ,3)))
          println(simplify(e8))
          val s8 = simplify(e8)
          val pe8 = prettyPrint(s8)
          println("8)  " + pe8)
          assert(pe8 == "12(χ^5)")

          //case 4 - constants percolated to front
          // sin(x)4xcos(x)3 = 12x sin(x)cos(x)
          val e9 = BinOp("*", BinOp("*", sin(χ), BinOp("*",4,χ)),  BinOp("*", cos(χ), 3))
          val pe9 = prettyPrint(simplify(e9))
          println("9)  " + pe9)
          //assert(pe9 == "12χsin(χ)cos(χ)")

          // tan(x) * (x^2) = (x^2) (tan(x))
          val e10 = BinOp("*",tan(χ), BinOp("^",χ,7))
          val pe10 = prettyPrint(simplify(e10))
          println("10) " + pe10)
          assert(pe10 == "(χ^7)tan(χ)")

          // sin(3x) * (7x^2)
          val e11 = BinOp("*", sin(χ), BinOp("*",7, BinOp("^",χ,2)))
          val pe11 = prettyPrint(simplify(e11))
          println("11) " + pe11)
          //assert(pe11 == "7(χ^2)sin(3χ)")

          // HELP todo
          // (x+3)(4x) / (2x) = 2x + 6
          val e12 = BinOp("/", BinOp("*", BinOp("+",χ,3), BinOp("*",4,χ)),  BinOp("*",2,χ))
          val pe12 = prettyPrint(simplify(e12))
          println("12) " + pe12)
          //assert(pe12 == "2χ + 6")

          val d1 = D(BinOp("/", BinOp("^", χ, 2), sin(BinOp("*", 3, χ))))
          println("d1) " + prettyPrint(simplify(d1)))
          simplify(d1)

          //testing real life deriv( -6cos^2(5x)/5) => 12sinxcosx
          // HELP not working
          val d2 = D(UnOp("-", BinOp("/", BinOp("*",6, BinOp("^", cos(BinOp("*",5,χ)), 2)), 5)))
          println(prettyPrint(d2))
          println("d2) " + prettyPrint(simplify(d2)))
     }
}
*/
