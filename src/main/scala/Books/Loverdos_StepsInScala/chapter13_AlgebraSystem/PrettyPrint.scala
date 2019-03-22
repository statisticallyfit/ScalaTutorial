package Books.Loverdos_StepsInScala.chapter13_AlgebraSystem


import Declarations._


object PrettyPrint {

     def prettyPrint(expr: Expression):String = expr match {
          case Var(v) => "hi there!"
          case v:Variable => v.toString
          case Num(n) => n.toString
          case Neg(e) => combineUnary("-", e)
          case Add(u, v) => combineBinary("+", u, v)
          case Sub(u, v) => combineBinary("-", u, v)
          case Mul(u, v) => combineBinary("*", u, v)
          case Div(u, v) => combineBinary("/", u, v)
          case Pow(u, v) => combineBinary("^", u, v)
          //"(" + prettyPrint(a) + ")" + op + "(" + prettyPrint(b) + ")"
          case Func1(name, e) =>
               name + "(" + prettyPrint(e) + ")"
          case Func2(name, a1, a2) => s"$name($a1, $a2)"
     }

     //note if precedence of an expr < opPrec then enclose expr in ( )
     def combineBinary(op:String, a:Expression, b:Expression) ={
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
                    case Pow(_,_) => s"($aStr)$bStr"
                    case Num(n) => b match {
                         case Pow(_,_) => s"$aStr($bStr)" // 12x^5  and   7(χ^2)sin(χ)
                         case _ => s"$aStr$bStr" //if a = 4 and b = 3 then result is 43 (not correct)
                    }
                    case _ => b match {
                         case Pow(_,_) => s"$aStr($bStr)"
                         case _ => s"$aStr$bStr"
                    }
               }
          }
          else if(op == "^") s"$aStr^$bStr"
          else s"$aStr $op $bStr"
     }

     def combineUnary(op:String, expr:Expression) ={
          val eStr = prettyPrint(expr)
          val subExpr =
               if(precedence(expr) < unPrec)
                    "(" + eStr + ")"
               else eStr

          op + subExpr
     }

     //the higher the precedence, the more important it is
     def precedence(expr: Expression) = expr match {
          case Add(_,_) => binPrec("+")
          case Sub(_,_) => binPrec("-")
          case Mul(_,_) => binPrec("*") //help todo
          case Div(_,_) => binPrec("/")
          case Pow(_,_) => binPrec("^")
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
}
