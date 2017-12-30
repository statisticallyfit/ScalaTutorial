package Books.Eckel_AtomicScala.types.TypeParameterConstraints

import scala.reflect.runtime.{universe=>ru}

/**
  *
  */
object Exercise1_HouseEnumTrait {
//trait color darker (before): 7F71FF

     //help todo: how to use this to collect all the trait/class names we encounter as we go from most
     //sublevel thing to highest, like Kitchen to Building? Going through Edible -> Storeable -> ...
     /*object Name {
          def className(o: Any): String = {
               val currentMirror = ru.runtimeMirror(getClass.getClassLoader)
               currentMirror.reflect(o).symbol.toString.replace('$', ' ').split(' ').last
          }
     }

     trait Name {
          override def toString: String = Name.className(this)
     }*/


     //note creating basic traits
     trait Building
     trait Room
     trait Storage
     trait Sink

     //note creating capability traits
     trait Storable
     trait Cleanable
     trait Cookable

     // note creating the bounded traits that describe Edible and Handheld
     trait Store[T <: Storable]  //can only store things inheriting from Storable
     trait Cook[T]               // can store anything (T is general, not inheriting from anything)
     trait Clean[T <: Cleanable] //can only store things inheriting from Cleanable

     //note creating traits to describe food types and utensil types
     trait Edible extends Cleanable with Storable
     trait Handheld extends Cleanable with Storable

     //note creating edible type protein with more detailed edible types underneath
     object Protein extends Enumeration {
          case class ProteinEdibleEnum() extends Val with Edible
          type Protein = ProteinEdibleEnum
          val beef, chicken, pork, fish = ProteinEdibleEnum()
     }

     //note creating edible type fruit with more detailed edible types underneath
     object Fruit extends Enumeration {
          case class FruitEdibleEnum() extends Val with Edible
          type Fruit = FruitEdibleEnum
          val apple, orange, kiwi, grape = FruitEdibleEnum()
     }

     import Protein._
     import Fruit._

     //help question: why use CutleryHandheldEnum() but why not use
     // just Cutlery when assigning to knife,fork...?
     //note creating handheld types with more detailed handheld types underneath
     object Cutlery extends Enumeration {
          case class CutleryHandheldEnum() extends Val with Handheld
          type Cutlery = CutleryHandheldEnum
          val knife, spoon, fork, spatula = CutleryHandheldEnum()
     }

     //note creating handheld types with more detailed handheld types underneath
     object Device extends Enumeration {
          case class DeviceHandheldWithEnum() extends Val with Handheld
          type Device = DeviceHandheldWithEnum
          val poacher, timer, sifter, torch = DeviceHandheldWithEnum()
     }

     import Cutlery._
     import Device._

     //note Cook is storable and cleanable (inherited from or equal to Edible).
     // trait Food has arguments typed under Edible
     // 1) created Storeable and Cleanable among others
     // 2) built up Edible / Handheld using Storeable and Cleanable
     // 3) built up Store[] and Clean[] with Storeable and Cleanable arguments
     // 4) used Edible and Handheld as mixins for specific enum types
     // 5) used Edible and Handheld as arg type upper bounds for specific traits Food / Utensil
     // 6) used Store[] and Clean[] as mixins with specific traits Food / Utensil

     trait Food[F <: Edible] extends Store[F] with Clean[F] with Cook[F]

     //note Cook is storable and cleanable (inherited from or equal to Handheld)
     trait Utensil[U <: Handheld] extends Store[U] with Clean[U] with Cook[U]



     trait Kitchen extends Room {
          val storage: Storage
          val sinks: Vector[Sink]
          // note Food takes on argument type equal to or inherited from Edible. In this case, there is
          // Edible directly.
          val food: Food[Edible]
          //note Utensil takes on argument type equal to or inherited from Handeld. In this case,
          // we get Handheld directly.
          val utensils: Vector[Utensil[Handheld]]
     }

     case class House() extends Building {
          val kitchens = Vector[Kitchen]()
     }


     def main(args: Array[String]) {

          assert(House.toString == "House")
     }
}
