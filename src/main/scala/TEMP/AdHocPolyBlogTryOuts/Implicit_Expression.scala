package TEMP.AdHocPolyBlogTryOuts

// note SOURCE: http://blog.jaceklaskowski.pl/2015/05/15/ad-hoc-polymorphism-in-scala-with-type-classes.html


object Implicit_Expression {

     sealed trait Expression
     case class Number(value: Int) extends Expression
     case class Plus(lhs: Expression, rhs: Expression) extends Expression
     case class Minus(lhs: Expression, rhs: Expression) extends Expression


     object ExpressionImplicits {
          implicit class ExpressionOps(e: Expression) {
               def value: Int = e match { // the expression's implicit
                    case n : Number => n.value
                    case p : Plus => p.value
                    case m : Minus => m.value
               }
          }

          implicit class PlusOps(p: Plus) { // the plus's implicit.
               def value: Int = p.lhs.value + p.rhs.value
          }

          implicit class MinusOps(m: Minus) {
               def value: Int = m.lhs.value - m.rhs.value
          }
     }


     def main(args: Array[String]) {

          import ExpressionImplicits._

          val expr1 = Plus(Minus(Number(5), Number(3)), Number(18))
          assert(expr1.value == 20, "Test 1")

          val expr2 = Minus(Plus(Number(19), Number(17)), Number(39))
          assert(expr2.value == -3, "Test 2")
     }
}