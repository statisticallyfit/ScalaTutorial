package Eckel_AtomicScala.traits.BuildingSystemsWithTraits


object Exercise2_Coffee extends App {

     object Shots extends Enumeration {
          type Shots = Value
          val Single, Double, Triple = Value
     }

     object Cup extends Enumeration {
          type Cup = Value
          val ToGo, Here = Value
     }

     object Caffeine extends  Enumeration{
          type Caffeine = Value
          val Caf, HalfCaf, Decaf = Value
     }

     object Milk extends  Enumeration {
          type Milk = Value
          val /*NoMilk,*/ Skim, Lowfat = Value
     }

     // note using _Val() extends Val with Flavor when we want to mixin a trait inside the object
     //help but couldn't we have done without the flavor trait mixed in here?
     object Syrup extends Enumeration {
          case class _Val() extends  Val with Flavor
          type Syrup = _Val
          val NoFlavor, Vanilla, Hazelnut, Raspberry, Chocolate = _Val()
     }

     trait Flavor
     trait Amount { val pumps: Int }
     trait Taste[F <: Flavor] extends Amount { val flavor: F}

     import Shots._
     import Caffeine._
     import Cup._
     import Milk._
     import Syrup._

     //help why are they no longer case classes?
     class Coffee(shots: Shots, caffeine: Caffeine, cupType: Cup, syrup: Syrup = NoFlavor){
          override def toString = s"Coffee($shots,$caffeine,$cupType,$syrup)"
     }

     class Latte(shots: Shots, caffeine: Caffeine, cupType: Cup, milk: Milk, syrup: Syrup)
          extends Coffee(shots, caffeine, cupType, syrup){
          override def toString = s"Latte($shots,$caffeine,$cupType,$milk,$syrup)"
     }



     val single = new Coffee(Single, Caf, Here, Chocolate)
     assert(single.toString == "Coffee(Single,Caf,Here,Chocolate)")
     println(single)

     val usual = new Coffee(Double, Caf, Here)
     assert(usual.toString == "Coffee(Double,Caf,Here,NoFlavor)")
     println(usual)

     val mocha = new Coffee(Double, HalfCaf, ToGo, Chocolate)
     assert(mocha.toString == "Coffee(Double,HalfCaf,ToGo,Chocolate)")
     println(mocha)

     val berry = new Coffee(Single, Decaf, ToGo, Raspberry)
     assert(berry.toString == "Coffee(Single,Decaf,ToGo,Raspberry)")
     println(berry)

     println("----------------------------------------------------------")

     val latte = new Latte(Single, Caf, Here, Skim, Vanilla)
     assert(latte.toString == "Latte(Single,Caf,Here,Skim,Vanilla)")
     println(latte)
}