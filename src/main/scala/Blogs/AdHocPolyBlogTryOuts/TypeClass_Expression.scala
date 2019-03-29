package Blogs.AdHocPolyBlogTryOuts

/**
  *
  */
object TypeClass_Expression {


     sealed trait Expression
     case class Number(value: Int) extends Expression
     case class Plus(lhs: Expression, rhs: Expression) extends Expression
     case class Minus(lhs: Expression, rhs: Expression) extends Expression




     //the typeclass
     trait Valueable {
          def value: Int
     }

     trait Value[T] { //more abstract, uses Valueable.
          def value(t: T): Valueable
     }



     // note: there's no Expressions, just Valueables.
     object CalculatorExpression {

          def calculate(v: Valueable): Int = v.value

          // context bound T: Value says that for any type T there is an
          // implicit conversion to a Value[_] type hierarchy using implicits.
          def calculate[T: Value](t: T): Int =
               calculate(implicitly[Value[T]].value(t))
     }



     object ExpressionImplicits {
          implicit class ExpressionOps(e: Expression) extends Valueable {
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

          // ---------------------------------------------------------------------

          implicit val number2Value = new Value[Number] {
               def value(n: Number): Valueable = new Valueable {
                    override def value: Int = n.value
               }
          }

          implicit val plus2Value = new Value[Plus] {
               def value(p: Plus): Valueable = new Valueable {
                    override def value: Int = p.lhs.value + p.rhs.value
               }
          }

          implicit val minus2Value = new Value[Minus] {
               def value(m: Minus): Valueable = new Valueable {
                    override def value: Int = m.lhs.value - m.rhs.value
               }
          }

          // ------------------------------------------------------------

          implicit val int2Value = new Value[Int] {
               override def value(t: Int) = new Valueable {
                    override def value: Int = t
               }
          }
     }


     def main (args: Array[String] ) {

          import ExpressionImplicits._


          val expr1 = Plus(Minus(Number(5), Number(3)), Number(18))
          assert(CalculatorExpression.calculate(expr1) == 20, "Test 1")

          val expr2 = Minus(Plus(Number(19), Number(17)), Number(39))
          assert(CalculatorExpression.calculate(expr2) == -3, "Test 2")

          /*val expr3 = Minus(Plus(28, 3), 12)
          assert(CalculatorExpression.calculate(expr3) == 19, "Test 3")*/
     }
}
