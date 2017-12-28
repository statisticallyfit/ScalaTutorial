package Eckel_AtomicScala.traits.BuildingSystemsWithTraits




object Exercise1_Coffee extends App {


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
          val NoMilk, Skim, Lowfat = Value
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


     case class Coffee(shots: Shots, caffeine: Caffeine, cups: Cup, milk: Milk, syrup: Syrup)


     val single = Coffee(Single, Caf, Here, Skim, Chocolate)
     assert(single.toString == "CoffeeTraits(Single,Caf,Here,Skim,Chocolate)")
     println(single)

     val usual = Coffee(Double, Caf, Here, NoMilk, NoFlavor)
     assert(usual.toString == "CoffeeTraits(Double,Caf,Here,NoMilk,NoFlavor)")
     println(usual)

     val mocha = Coffee(Double, HalfCaf, ToGo, Skim, Chocolate)
     assert(mocha.toString == "CoffeeTraits(Double,HalfCaf,ToGo,Skim,Chocolate)")
     println(mocha)

     val berry = Coffee(Single, Decaf, ToGo, Lowfat, Raspberry)
     assert(berry.toString == "CoffeeTraits(Single,Decaf,ToGo,Lowfat,Raspberry)")
     println(berry)
}