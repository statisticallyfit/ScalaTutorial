package Eckel_AtomicScala.patternmatching.PatternMatchingWithCaseClasses

import scala.collection.mutable.ArrayBuffer

/**
  *
  */
object Exercise1_2_3_Passenger {

     case class Passenger(first:String, last:String)
     case class Train(travelers:Vector[Passenger], line:String)
     case class Bus(passengers:Vector[Passenger], capacity:Int)
     case class Plane(passengers:Vector[Passenger], planeName:String)
     case class Kitten(name:String)

     def travel(transport:Any):String ={
          transport match {
               case Train(travelers, line) => s"Train line $line $travelers"
               case Bus(travelers, seats) => s"Bus size $seats $travelers"
               case Plane(travelers, name) => s"Plane name $name $travelers"
               // help why not matched? //case Passenger => "reached object"
               case p:Passenger => s"${p.first} is walking"
               case what => s"$what is in limbo!"
          }
     }


     def main(args: Array[String]) {

          val travelers = Vector(
               Passenger("Harvey", "Rabbit"),
               Passenger("Dorothy", "Gale")
          )

          val trip = Vector(
               Train(travelers, "Reading"),
               Bus(travelers, 100),
               Plane(travelers, "Boeing")
          )

          assert(travel(Passenger("Sally", "Marie")) == "Sally is walking")
          assert(travel(Kitten("Kitty")) == "Kitten(Kitty) is in limbo!")
          assert(travel(trip(0)) == "Train line Reading " +
               "Vector(Passenger(Harvey,Rabbit), Passenger(Dorothy,Gale))")
          assert(travel(trip(1)) == "Bus size 100 " +
               "Vector(Passenger(Harvey,Rabbit), Passenger(Dorothy,Gale))")
          assert(travel(trip(2)) == "Plane name Boeing " +
               "Vector(Passenger(Harvey,Rabbit), Passenger(Dorothy,Gale))")
     }
}
