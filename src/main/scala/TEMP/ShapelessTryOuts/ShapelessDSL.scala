package TEMP.ShapelessTryOuts


import shapeless.{ ::, Generic, HList, HNil, Lazy}

/**
  *
  */

trait Amount[A]{
     def zero: A
     def plus(a: A, b: A): A
     def times(a: A, b: BigDecimal): A
}

object Amount {
     implicit val amountForBigDecimalHList: Amount[BigDecimal :: HNil] = new Amount[BigDecimal :: HNil] {

          override def zero: BigDecimal :: HNil = BigDecimal(0) :: HNil
          override def plus(a: BigDecimal :: HNil, b: BigDecimal :: HNil): BigDecimal :: HNil =
               (a.head + b.head) :: HNil

          override def times(a: BigDecimal :: HNil, b: BigDecimal): BigDecimal :: HNil =
               (a.head * b) :: HNil
     }

     //Implicit resolution used to get correct Generic instance of our case class.
     //note: type Repr = BigDecimal :: HNil in this case but it can be anything.
     // note: Generic.Aux[T, Repr0] is just sugar for setting type Aux[T, Repr0] = Generic[T] { type Repr = Repr0}
     implicit def genericAmount[A, R](implicit gen: Generic.Aux[A, R],amount: Amount[R]): Amount[A] = new Amount[A] {

          override def zero: A = gen.from(amount.zero)
          override def plus(a: A, b: A): A = gen.from(amount.plus(gen.to(a), gen.to(b)))
          override def times(a: A, b: BigDecimal): A = gen.from(amount.times(gen.to(a), b))
     }

     //The way to generate an Amount type class instance for HLists so that the resulting typeclass
     // can be plugged into genericAmount[A, R] where R is the HList.
     implicit def amountForHList[H, T <: HList](
                                                    implicit amount: Lazy[Amount[H]], defaultTail: DefaultInstance[T]
                                               ): Amount[H :: T] = new Amount[H :: T] {

          override def zero: H :: T = amount.value.zero :: defaultTail.instance
          override def plus(a: H :: T, b: H :: T): H :: T = amount.value.plus(a.head, b.head) :: defaultTail.instance
          override def times(a: H :: T, b: BigDecimal): H :: T = amount.value.times(a.head, b) :: defaultTail
               .instance
     }

     implicit class AmountOps[A](a: A)(implicit amount: Amount[A]) {
          def +(b: A): A = amount.plus(a, b)
          def plus(b: A): A = amount.plus(a, b)
          def -(b: A): A = amount.plus(a, amount.times(b, -1))
          def *[B](b: BigDecimal): A = amount.times(a, b)
          def /[B](b: BigDecimal): A = amount.times(a, BigDecimal(1) / b)
     }
}



trait DefaultInstance[T] {
     def instance: T
}

object DefaultInstance {

     def apply[A](a: A): DefaultInstance[A] = new DefaultInstance[A] {
          override def instance = a
     }

     implicit def defaultInstanceForHList[H, T <: HList](
          implicit defaultHead: DefaultInstance[H], defaultTail: DefaultInstance[T]
     ): DefaultInstance[H :: T] = new DefaultInstance[H :: T] {
          override def instance: H :: T = defaultHead.instance :: defaultTail.instance
     }

     // Now making the stop case - defaultinstance for hnil
     implicit val defaultInstanceForHNil: DefaultInstance[HNil] = new DefaultInstance[HNil] {
          override def instance: HNil = HNil
     }
}




object Period {
     sealed trait Month
     sealed trait Year
     final val Month = new Month() {}
     final val Year = new Year() {}

     implicit val defaultInstanceForMonth: DefaultInstance[Month] = DefaultInstance(Month)
     implicit val defaultInstanceForYear: DefaultInstance[Year] = DefaultInstance(Year)
}

object Surface {
     sealed trait SquareMeter
     final val SquareMeter = new SquareMeter {}
     implicit val defaultInstanceForSquareMeter: DefaultInstance[SquareMeter] = DefaultInstance(SquareMeter)
}

case class Per[V, U](value: V, unit: U)
object Per {
     implicit class PrettyConstructorForPer[A](a: A) {
          def per[B](b: B) = Per(a, b)
     }
}






object ShapelessDSL extends App {

     import DefaultInstance._
     import Period._
     import Per._
     import Surface._
     import Amount._

     case class GBP(value: BigDecimal)
     case class EUR(value: BigDecimal)
     case class USD(value: BigDecimal)

     //is this a highlighting error????
     val twentyPounds         = GBP(15) + GBP(5)
     println(twentyPounds)
     println("Thirty: " + GBP(15).plus(GBP(15)))

     val twentyPoundsPerMonth = twentyPounds per Month
     val fortyPoundsPerMonth  = twentyPoundsPerMonth + twentyPoundsPerMonth
     val pricePerSquareMeter  = EUR(1000) per SquareMeter
     val expensiveSquareMeter = pricePerSquareMeter + pricePerSquareMeter
     val rentingPotential     = USD(1800) per SquareMeter per Year
     val estimatedCost        = USD(1500) per SquareMeter per Year
     val estimatedRevenue     = rentingPotential - estimatedCost
}
