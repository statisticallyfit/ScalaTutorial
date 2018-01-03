package Books.StepsInScala_Loverdos.chapter13_AlgebraSystem


import Declarations._
import Derivative._
import Simplify._
import PrettyPrint._



object Test extends App {


     println(2 * (X + 1) * 8 * 3 * X * 1)
     println(clean(2 * (X + 1) * 8 * 3 * X * 1))
     println(prettyPrint(clean(2 * (X + 1) * 8 * 3 * X * 1))) //48x(x+1)
     println(prettyPrint(clean(4*(X + 2) + 5*X)))

     ///------

     // 3 cos(3x) 2 = 6 cos(3x)
     val e1 = 3 * cos(3*X) * 2
     val pe1 = prettyPrint(clean(e1))
     println("1)  " + pe1)
     //assert(pe1 == "6cos(5χ)")

     // sin(3x) 2 = 2sin(3x)
     val e2 = sin(3*X) * 2
     val pe2 = prettyPrint(clean(e2))
     println("2)  " + pe2)
     //assert(pe2 == "2sin(3χ)")

     // -sin(3x) 2 = -2sin(3x)
     val e3 =  -sin(3*X) * 2
     val pe3 = prettyPrint(clean(e3))
     println("3)  " + pe3)
     //assert(prettyPrint(simplify(e3)) == "-2sin(3χ)")

     // -sin(3x) (-2) = 2sin(3x)
     val e4 = -sin(3*X) * (-2)
     val s4 = clean(e4)
     val pe4 = prettyPrint(s4)
     println("4)  " + pe4)
     //assert(pe4 == "2sin(3χ)")

     // sin(x) 4x = 4x sin(x)
     val e5 = sin(X) * 4*X
     val s5 = clean(e5)
     val pe5 = prettyPrint(s5)
     println("5)  " + pe5)
     //assert(pe5 == "4χsin(χ)")

     // case 1 - constants are percolated to the front
     // (χ^2)(3cos(3χ))) = 3(x^2)cos(3x)
     //HELP not working for real life
     val e6 = (X^2) * (3 * cos(5*X))
     val pe6 = prettyPrint(clean(e6))
     println("6)  " + pe6)
     //assert(pe6 == "3(χ^2)cos(5χ)")

     // case 2 - constants are percolated to the front
     //(3sin(x))(4cos(x)) = 12sin(x)cos(x)
     // HELP todo
     val e7 = 3 * sin(X) * 4 * cos(X)
     val s7 = clean(e7)
     val pe7 = prettyPrint(s7)
     println("7)  " + pe7)
     //assert(pe7 == "12sin(χ)cos(χ)")

     //case 3 - constants percolated to front
     // 3x^2 * 4y^3 = 12 (x^2)(y^3)
     // 3e1 * 4e2 = 12 e1 e2
     // HELP
     val e8 = 3*X^2 * 4*X^3
     val s8 = clean(e8)
     val pe8 = prettyPrint(s8)
     println("8)  " + pe8)
     //assert(pe8 == "12(χ^5)")

     //case 4 - constants percolated to front
     // sin(x)4xcos(x)3 = 12x sin(x)cos(x)
     val e9 = sin(X) * 4 * cos(X) * 3
     val pe9 = prettyPrint(clean(e9))
     println("9)  " + pe9)
     //assert(pe9 == "12χsin(χ)cos(χ)")

     // tan(x) * (x^2) = (x^2) (tan(x))
     val e10 = tan(X) * X^2
     val pe10 = prettyPrint(clean(e10))
     println("10) " + pe10)
     //assert(pe10 == "(χ^2)tan(χ)")

     // sin(3x) * (7x^2)
     val e11 = sin(3*X) * 7*X^2
     val pe11 = prettyPrint(clean(e11))
     println("11) " + pe11)
     //assert(pe11 == "7(χ^2)sin(3χ)")

     // HELP todo
     // (x+3)(4x) / (2x) = 2x + 6
     val e12 = (X + 3) * (4*X)  / (2*X)
     val pe12 = prettyPrint(clean(e12))
     println("12) " + pe12)
     println("12) " + (X + 3) * (4*X)  / 2*X)
     //assert(pe12 == "2χ + 6")

     val d1 = d(X^2 / sin(3*X))(X)
     val sd1 = clean(d1)
     val pd1 = prettyPrint(sd1)
     println("d1) " + pd1)

     //testing real life deriv( -6cos^2(5x)/5) => 12sinxcosx
     // HELP not working
     val d2 = d(Neg(-6*cos(5*X)^2 / 5))(X)
     val sd2 = clean(d2)
     val pd2 = prettyPrint(sd2)
     println("d2) " + pd2)


     //assert(D(sin(3*X), X) == "3cos(3χ)")

     val d3 = d(sin(3*X))(Y)
     println(d3)
     val sd3 = clean(d3)
     println(sd3)
     println(prettyPrint(d3))

     //assert(D(sin(3*X), Y) == "0")

     //assert(D(Y * sin(3*X), Y) == "sin(3χ)")

     //assert(D(sin(X*Y), Y) == "χcos(χγ)")

     //assert(D(sin(X*Y), X) == "γcos(χγ)")

     //assert(D(sin(Y*X), X) == "γcos(γχ)")
}


// a*e1*b*e2 = a*b*e1*e2
/*case BinOp("*", BinOp("*",Num(a),BinOp("^",e1,Num(n))),BinOp("*",Num(b),BinOp("^",e2,Num(m)))) =>{
     if(e1 == e2)
          BinOp("*", a * b, BinOp("^", simplify(e1), n + m))
     else //ax^3 * cy^5 = (a*c) x^3y^5
          BinOp("*", a * b, BinOp("*", BinOp("^", simplify(e1), n),
               BinOp("^", simplify(e2), m)))
}*/
// a*e1*b*e2 = a*b*e1*e2
/*case BinOp("*", BinOp("*", Num(a), e1), BinOp("*", Num(b), e2)) =>
     BinOp("*", a * b, BinOp("*", simplify(e1), simplify(e2)))*/

/*//sin(3x)2 = 2sin(3x)
          case BinOp("*", f@Function1(_,_), Num(n)) => BinOp("*", Num(n), simplify(f))
          //-sin(3x)2 = 2sin(3x)
          case BinOp("*", UnOp("-", f@Function1(_,_)), Num(n)) => BinOp("*", -n, simplify(f))
          // sin(3x) 2x = 2x sin(3x)
          case  BinOp("*", f@Function1(_,_), b@BinOp(_,_,_)) => BinOp("*", simplify(b), simplify(f))
          // sin(x) x = x sin(x)
          case BinOp("*", f@Function1(_,_), X) => BinOp("*", X, simplify(f))
          //(2)(3)cos(3x) = 6cos(3x)
          // 1
          case BinOp("*", Num(n), BinOp("*", Num(m), f@Function1(_,_))) =>
               BinOp("*", n * m, simplify(f))
          // 2
          case BinOp("*", BinOp("*", Num(n), Num(m)), f@Function1(_,_)) =>
               BinOp("*", n * m, simplify(f))
          //2cos(3x)3 = 6cos(3x)
          // 1
          case BinOp("*", Num(n), BinOp("*", f@Function1(_,_), Num(m))) =>
               BinOp("*", n * m, simplify(f))
          // 2
          case BinOp("*", BinOp("*", Num(n), f@Function1(_,_)), Num(m)) =>
               BinOp("*", n * m, simplify(f))
          // cos(3x)(2)(3) = 6cos(3x)
          // 1
          case BinOp("*", BinOp("*", f@Function1(_,_), Num(m)), Num(n)) =>
               BinOp("*", n * m, simplify(f))
          // 2
          /*case BinOp("*", f@Function(_,_), BinOp("*", Num(m), Num(n))) =>
               BinOp("*", n * m, simplify(f))*/
          // (x^2) (3cos(5x)) = (3x^2) (cos(5x))
          case BinOp("*", BinOp("^",u,v), BinOp("*", Num(n), f@Function1(_,_))) =>
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
          //case BinOp("*", BinOp())*/

//case BinOp(op, left, right) => BinOp(op, simplify(left), simplify(right))
//help why don't work?
/*case x + y => simplify(x) + simplify(y)
case x - y => simplify(x) - simplify(y)
case x * y => simplify(x) * simplify(y)
case x / y => simplify(x) / simplify(y)
case x ^ y => simplify(x) ^ simplify(y)*/