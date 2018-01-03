package Books.AtomicScala.traits.BuildingSystemsWithTraits




object MaltShoppeExample extends App {


     object Quantity extends Enumeration {
          type Quantity = Value
          val None, Small, Regular, Extra, Super = Value
     }

     object Holder extends Enumeration {
          type Holder = Value
          val Bowl, Cup, Cone, WaffleCone = Value
     }

     object Syrup extends Enumeration {
          case class _Val() extends Val  with Flavor
          type Syrup = _Val
          val Chocolate, HotFudge, Butterscotch, Caramel = _Val()
     }

     object IceCream extends  Enumeration {
          case class _Val() extends Val with Flavor
          type IceCream = _Val
          val Chocolate, Vanilla, Strawberry, Coffee, MochaFudge, RumRaisin, ButterPecan = _Val()
     }

     object Sprinkle extends Enumeration {
          case class _Val() extends Val with Flavor
          type Sprinkle = _Val
          val None, Chocolate, Rainbow = _Val()
     }

     import Quantity._
     import Holder._
     import Syrup._
     import IceCream._
     import Sprinkle._

     //note made taste inherit amount so that we can use either just amount or just
     // taste (which includes amount as well) because nuts, whippedcream, cherry don't
     //need taste so we just use amount but others use taste (more descriptive)
     trait Flavor
     trait Amount {val quant: Quantity}
     trait Taste[F <: Flavor] extends Amount { val flavor: F}
     trait Topping

     case class Scoop(quant: Quantity, flavor: IceCream) extends Taste[IceCream]
     case class Sprinkles(quant: Quantity, flavor: Sprinkle) extends Taste[Sprinkle] with Topping
     case class Sauce (quant: Quantity, flavor: Syrup) extends Taste[Syrup] with Topping
     case class WhippedCream(quant: Quantity) extends Amount with Topping
     case class Nuts(quant: Quantity) extends Amount with Topping
     class Cherry extends Topping





     // ------------------------------------------------------
     //note the scoops is not a set list or seq, just a variable list of
     // arguments because the scoops aren't made to order, they can
     // be eaten at the store and no precautions must be taken.
     case class Scoops(holder: Holder, scoops: Scoop*)

     val iceCreamCone = Scoops(
          WaffleCone,
          Scoop(Extra, MochaFudge),
          Scoop(Extra, ButterPecan),
          Scoop(Extra, IceCream.Chocolate)
     )

     println(iceCreamCone)
     assert(iceCreamCone.toString == "Scoops(WaffleCone,WrappedArray(Scoop(Extra,MochaFudge), Scoop" +
          "(Extra,ButterPecan), Scoop(Extra,Chocolate)))")

     // ------------------------------------------------------
     //note the scoops and toppings are made into sequences to be more orderly
     // because the goodies are made to order
     case class MadeToOrder(holder: Holder, scoops: Seq[Scoop], toppings: Seq[Topping])

     val iceCreamDish = MadeToOrder(
          Bowl,
          Seq(
               Scoop(Regular, Strawberry),
               Scoop(Regular, ButterPecan)
          ),
          Seq[Topping]()
     )

     println(iceCreamDish)
     assert(iceCreamDish.toString == "MadeToOrder(Bowl,List(Scoop(Regular,Strawberry), Scoop(Regular," +
          "ButterPecan)),List())")

     // ------------------------------------------------------
     case class Sundae(sauce: Sauce,
                       sprinkles: Sprinkles,
                       whipped: WhippedCream,
                       nuts: Nuts,
                       scoops: Scoop*){
          val holder: Holder = Bowl
     }

     val hotFudgeSundae = Sundae(
          Sauce(Regular, HotFudge),
          Sprinkles(Regular, Sprinkle.Chocolate),
          WhippedCream(Regular),
          Nuts(Regular),
          Scoop(Regular, Coffee),
          Scoop(Regular, RumRaisin)
     )

     println(hotFudgeSundae)
     assert(hotFudgeSundae.toString == "Sundae(Sauce(Regular,HotFudge),Sprinkles(Regular,Chocolate)," +
          "WhippedCream(Regular),Nuts(Regular),WrappedArray(Scoop(Regular,Coffee), Scoop(Regular,RumRaisin)))")

}