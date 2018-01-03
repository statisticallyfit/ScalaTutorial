package Books.StepsInScala_Loverdos.chapter13_AlgebraSystem

import Declarations._
import Books.lib.Fraction

import scala.math.Pi

/**
  * PLAN TODO HELP: instead of this mess below, put the Expression in a tree following order
  *  of operations. Then define instances of functor and traversable to map over the tree
  *  to do these things:
  *
  *  key 1) if we encounter case X * 3 then return 3 * X, where X = function, X, or another expression
  *
  *  key 2) if we encounter constants floating around the expression, squash them, so
  *  this: 3sinx8cosx will result in 24sinxcosx
  *
  */
object Simplify {


     def clean(expr: Expression): Expression = expr match {
          case v:Variable => v
          case Num(n) => Num(n)
          case Func1(name, arg) => Func1(name, clean(arg))
          case Func2(name, a1, a2) => Func2(name, clean(a1), clean(a2))
          case Neg(Num(n)) => Num(-n)
          case Neg(Mul(u, Neg(v))) => Mul(clean(u), clean(v))
          case Neg(Mul(Neg(u), v)) => Mul(clean(u), clean(v))
          case Neg(Neg(e)) => clean(e)
          case Neg(e) => Neg(clean(e))
          case Add(Num(n), Num(m)) => n + m
          case Add(Num(0), e) => clean(e)
          case Add(e, Num(0)) => clean(e)
          case Sub(Num(n), Num(m)) => n - m
          case Sub(e, Num(0)) => clean(e)
          case Sub(Num(0), e) => Neg(clean(e))
          case Mul(Num(n), Num(m)) => n * m
          case Mul(Num(0), _) => Num(0)
          case Mul(_, Num(0)) => Num(0)
          case Mul(Num(1), e) => clean(e)
          case Mul(e, Num(1)) => clean(e)
          case Mul(Neg(u), Neg(v)) => Mul(clean(u), clean(v))
          case Mul(Neg(u), v) => Neg(Mul(clean(u), clean(v)))
          case Mul(u, Neg(v)) => Neg(Mul(clean(u), clean(v)))
          case Div(Num(0), e) => Num(0)
          case Div(Num(n), Num(m)) => Fraction(n, m)
          case Div(e, Num(1)) => clean(e)
          case Pow(Num(n), Num(m)) => Math.pow(n, m).toInt
          case Pow(e, Num(1)) => clean(e)
          //e*n + e*m = e(n+m)
          //help todo is this const cleaning?
          /*case Add(Mul(e1, n), Mul(e2, m)) => if(e1 == e2) e1 * (n + m)
          else clean(e1) * n + clean(e2) * m*/

          //sin^2 x + cos^2 x = 1
          case Add(Pow(sin(u), Num(2)), Pow(cos(v), Num(2))) => if(u == v) 1
          else sin(clean(u))^2 + cos(clean(v))^2
          // tan^2 x + 1 = sec^2 x
          case Add(Pow(tan(u), Num(2)), Num(1)) => sec(clean(u))^2
          // 1 + cot^2 x = csc^2 x
          case Add(Pow(cot(u), Num(2)), Num(1)) => csc(u)^2
          // sin(-x) = -sin x , cos, tan
          case sin(Neg(u)) => -sin(clean(u))
          case cos(Neg(u)) => cos(clean(u))
          case tan(Neg(u)) => -tan(clean(u))
          case sec(Neg(u)) => sec(clean(u))
          case csc(Neg(u)) => -csc(clean(u))
          case cot(Neg(u)) => -cot(clean(u))
          // sin(pi/2 - x) = cos x
          case sin(Sub(Div(Num(Pi), Num(2)), u)) => cos(clean(u))
          case cos(Sub(Div(Num(Pi), Num(2)), u)) => sin(clean(u))
          // double angle formulas HELP TODO expand for all positions combinations
          // 2 sin x cos x = sin(2x)
          case Mul(Num(2), Mul(sin(u), cos(v))) =>
               if(u == v) sin(2 * clean(u))
               else 2 * sin(clean(u)) * cos(clean(v))
          // cos^2 x - sin^2 x = cos(2x)
          case Sub(Pow(cos(u), Num(2)), Pow(sin(v), Num(2))) =>
               if(u == v) cos(2 * clean(u))
               else cos(clean(u))^2 - sin(clean(v))^2
          // 2cos^2 x - 1 = cos(2x)
          case Sub(Mul(Num(2), Pow(cos(u), Num(2))), Num(1)) => cos(2 * clean(u))
          // 1 - 2sin^2 x = cos(2x)
          case Sub(Num(1), Mul(Num(2), Pow(sin(u), Num(2)))) => cos(2 * clean(u))
          // 2 tan x / (1 - tan^2 x)
          case Div(Mul(Num(2), tan(u)), Sub(Num(1), Pow(tan(v), Num(2)))) =>
               if(u == v) tan(2 * clean(u))
               else 2 * tan(clean(u)) / (1 - tan(clean(v))^2)
          // sin x * 1 / csc x = 1
          case Mul(sin(u), csc(v)) => if(u == v) 1 else sin(clean(u)) * csc(clean(v))
          case Mul(cos(u), sec(v)) => if(u == v) 1 else cos(clean(u)) * sec(clean(v))
          case Mul(tan(u), cot(v)) => if(u == v) 1 else tan(clean(u)) * cot(clean(v))
          case Mul(csc(u), sin(v)) => if(v == u) 1 else sin(clean(v)) * csc(clean(u))
          case Mul(sec(u), cos(v)) => if(v == u) 1 else cos(clean(v)) * sec(clean(u))
          case Mul(cot(u), tan(v)) => if(v == u) 1 else tan(clean(v)) * cot(clean(u))

          //simple dividing out simplification
          case Div(Mul(u, v), w) =>
               if(u == w) clean(v) / clean(w)
               else if(v == w) clean(u) / clean(w)
               else clean(u) * clean(v) / clean(w)

          // ------------------------ Apply power and log rules ------------------------
          case Pow(u, Num(0)) => clean(u)
          case Pow(u, Neg(Num(n))) => 1 / clean(u) ^ n
          case Mul(Pow(u, Num(n)), Pow(v, Num(m))) =>
               if(n == m) (clean(u) * clean(v)) ^ n
               else if (u == v) clean(u) ^ (m + n)
               else (clean(u) ^ n) * (clean(v) ^ m)
          case Mul(u, Pow(v, Num(m))) =>
               if (u == v) clean(u) ^ (m + 1)
               else clean(u) * (clean(v) ^ m)
          case Div(Pow(u, Num(m)), Pow(v, Num(n))) =>
               if(m == n) (clean(u) / clean(v)) ^ m
               else if(u == v) clean(u) ^ (m - n)
               else (clean(u) ^ m) / (clean(v) ^ m)
          case Pow(Pow(u, Num(m)), Num(n)) => clean(u) ^ (m * n)
          case Mul(Sub(a, b), Add(a1, b1)) =>
               if (a == a1 && b == b1) clean(a)^2 - clean(b)^2
               else if(a == b1 && b == a1) clean(a)^2 - clean(b)^2
               else (clean(a) - clean(b)) * (clean(a1) + clean(b1))
          case Mul(Add(a, b), Sub(a1, b1)) => clean(Mul(Sub(a1, b1), Add(a, b)))
          // help todo (a + b)^2 = a^2 + b^2 + 2ab
          //case Add(Pow(a, Num(2)), Add(Pow(b, Num(2)), Mul(Num(2), Mul(a1, b1))))
          case log(b, Mul(m, n)) => log(clean(b), clean(m)) + log(clean(b), clean(n))
          case log(b, Div(m, n)) => log(clean(b), clean(m)) - log(clean(b), clean(n))
          case log(b, Pow(m, p)) => clean(p) * log(clean(b), clean(m))
          case log(b, Num(1)) => 0
          case log(Pow(b, m), a) => log(clean(b), clean(a)) / clean(m)
          case log(u, v) => if(u == v) 1 else log(clean(u), clean(v))
          case Pow(u, log(b, a)) =>
               if(u == a) clean(b) else clean(u)^log(clean(b), clean(a))


          // ------------------------ Pull out constants to the front ------------------------
          case Mul(l, r) => val left = clean(l); val right = clean(r)
               (left, right) match {
                    case (Mul(Num(d1), x), Mul(Num(d2), y)) => Mul(d1*d2, Mul(x, y))
                    case (x, Mul(c: Num, y)) => Mul(c, Mul(x, y))
                    case (Mul(c: Num, x), y) => Mul(c, Mul(x, y))
                    case (Mul(u, x), Mul(v, y)) => Mul(Mul(u, v), Mul(x, y))
                    case (x, Mul(u, y)) => Mul(u, Mul(x, y))
                    case (Mul(u, x), y) => Mul(u, Mul(x, y))
                    case _ => Mul(left, right)
               }

          case Div(l, r) => val top = clean(l); val bottom = clean(r)
               (top, bottom) match {
                    case (Mul(Num(a), x), Mul(Num(b), y)) => Fraction(a, b) * x / y
                    case (x, Mul(Num(b), y)) => Mul(Fraction(1, b), Div(x, y) )
                    case (Mul(a: Num, x), y) => Mul(a, Div(x, y) )
                    case (Mul(u, x), Mul(v, y)) => Mul(Div(u, v), Div(x, y) )
                    case (x, Mul(u, y)) => (1 / u) * (x / y)
                    case (Mul(u, x), y) => Mul(u, Div(x, y) )
                    case _ => Div(top, bottom)
               }
          /*case Mul(u, Num(n)) => n * clean(u)
          case Mul(u, Frac(f)) => f * clean(u)

          case Mul(l, r) => val left = clean(l); val right = clean(r)
               (left, right) match {
                    case (Num(n), Mul(Num(m), u)) => n * m * clean(u) //above is prereq
                    case (Mul(Num(n), u), Mul(Num(m), v)) => n * m * clean(u) * clean(v)
                    case (u, Mul(Num(n), v)) => n * clean(u) * clean(v)
                    case (Mul(Num(n), u), v) => n * clean(u) * clean(v)
                    case (Mul(u, v), Mul(s, t)) => clean(u) * clean(v) * clean(s) * clean(t)
                    case (u, Mul(s, t)) => clean(u) * clean(s) * clean(t)
                    case (Mul(u, v), s) => clean(u) * clean(v) * clean(s)
                    case (u, v:Variable) => v * clean(u)
                    case _ => if(left == right) left^2 else clean(left) * clean(right)
               }

          case Div(l, r) => val top = clean(l); val bottom = clean(r)
               (top, bottom) match {
                    case (Mul(Num(n), u), Mul(Num(m), v)) => Fraction(n,m) * clean(u) / clean(v)
                    case (u, Mul(Num(m), v)) => Fraction(1, m) * clean(u) / clean(v)
                    case (Mul(Num(n), u), v) => n * clean(u) / clean(v)
                    case (Mul(u, v), Mul(s, t)) => (clean(u) / clean(v)) * (clean(s) / clean(t))
                    case _ => if(top == bottom) 1 else clean(top) / clean(bottom)
               }*/



          // --------------------- Simplify 4(x+1) + 5x type expressions ---------------------
          //help todo
          case Add(Mul(Num(m), u), Mul(Num(n), v)) =>
               if(u == v) (n + m) * clean(u)
               else (m * clean(u)) + (n * clean(v))
          case Add(u, Mul(Num(n), v)) =>
               if(u == v) (n + 1) * clean(u)
               else clean(u) + (n * clean(v))
          case Add(Mul(Num(n), v), u) =>
               if(u == v) (n + 1) * clean(u)
               else clean(u) + (n * clean(v))
          case Sub(Mul(Num(m), u), Mul(Num(n), v)) => clean(Add(u*m, -n*v))
          case Sub(u, Mul(Num(n), v)) => clean(Add(u, -n*v))
          case Sub(Mul(Num(n), v), u) => clean(Add(n*v, -u))

          // last case general simplification
          case Add(u, v) => clean(u) + clean(v)
          case Sub(u, v) => clean(u) - clean(v)
          case Pow(u, v) => clean(u) ^ clean(v)
     }
}
