package ProjectCalculusSimplifier

/**
  *
  */
abstract class Encoded[C <: Encoded[C]] {

     def add(x:C, y:C): C = ???
     def mul(x:C, y:C): C = ???
     def div(x:C, y:C): C = ???
}


//Number types Const
class Fraction

class Const
class Whole(int: Int) extends Const
class Quotient(quot: Fraction) extends Const



//The encoded types
class Zero extends Encoded[Zero]

///////
case class Monomial(coef: Const, power: Const) extends Encoded[Monomial] {
     def add(that: Monomial) = ???
     def mul(that: Monomial) = ???
     def div(that: Monomial) = ???
     def get() = (coef, power)
}

///////
case class Polynomial(coefs: List[Const]) extends Encoded[Polynomial] {
     def add(that: Polynomial) = ???
     def mul(that: Polynomial) = ???
     def div(that: Polynomial) = ???
     def get() = coefs
}


///////
abstract class Trigonometric[C <: Encoded[C]](argPowTuples: List[(Encoded[C], Encoded[C])])
     extends Encoded[Trigonometric[C]]

case class Trig[C <: Encoded[C]](argPowTuples: List[(Encoded[C], Encoded[C])])
     extends Trigonometric(argPowTuples) {

     def add(that: Trig[C]) = that //???
     def mul(that: Trig[C]) = that //???
     def div(that: Trig[C]) = that // ???
}
case class InvTrig[C <: Encoded[C]](argPowTuples: List[(Encoded[C], Encoded[C])])
     extends Trigonometric(argPowTuples) {

     def add(that: InvTrig[C]) = that //???
     def mul(that: InvTrig[C]) = that //???
     def div(that: InvTrig[C]) = that //???
}

///////
abstract class Hyperbolic[C <: Encoded[C]](argPowTuples: List[(Encoded[C], Encoded[C])])
     extends Encoded[Hyperbolic[C]]

case class Hyper[C <: Encoded[C]](argPowTuples: List[(Encoded[C], Encoded[C])])
     extends Hyperbolic(argPowTuples) {

     def add(that: Hyper[C]) = that //???
     def mul(that: Hyper[C]) = that //???
     def div(that: Hyper[C]) = that //???
}
case class InvHyper[C <: Encoded[C]](argPowTuples: List[(Encoded[C], Encoded[C])])
     extends Hyperbolic(argPowTuples) {

     def add(that: InvHyper[C]) = that //???
     def mul(that: InvHyper[C]) = that //???
     def div(that: InvHyper[C]) = that //???
}

///////
class Logarithmic[C <: Encoded[C]](base: Encoded[C], arg: Encoded[C]) extends Encoded[Logarithmic[C]] {
     def add(that: Logarithmic[C]) = that //???
     def mul(that: Logarithmic[C]) = that //???
     def div(that: Logarithmic[C]) = that //???
}







object Util {
     ////////
     // method fill zeroes - the error thrown here (polymorphic function)
     /*def fillZeroes[C <: Encoded[C]](c1: C, c2: C)/*: (C, C)*/ ={
          val zero: Const = new Whole(0)

          (c1, c2) match {
               case (m1@Monomial(_, _), m2@Monomial(_, _)) => (m1, m2)

               case (Polynomial(xs), Polynomial(ys)) => {
                    val res = filler[Const](zero, xs, ys)

                    (Polynomial(res._1), Polynomial(res._2))
               }
               case (Trig(ts), Trig(ys)) => {
                    val res = filler(new Zero, ts, ys)
                    (Trig(res._1), Trig(res._2))
               }

          }

     }

     def filler[T](zero: T, xs: List[T], ys: List[T]): (List[T], List[T]) ={
          val zeroes: List[T] = List.fill[T](Math.abs(xs.length - ys.length))(zero)

          if (xs.length > ys.length)
               (xs, ys ++ zeroes)
          else if (xs.length < ys.length)
               (xs ++ zeroes, ys)
          else
               (xs, ys)
     }*/
}


