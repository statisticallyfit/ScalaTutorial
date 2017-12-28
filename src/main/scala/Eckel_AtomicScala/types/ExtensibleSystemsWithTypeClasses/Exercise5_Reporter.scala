package Eckel_AtomicScala.types.ExtensibleSystemsWithTypeClasses

/**
  *
  */
object Exercise5_Reporter {


     trait Reporter[I] {
          def generate(info: I): String
     }

     def report[I](info: I)(implicit reporter: Reporter[I]): String ={
          s"Reporting: ${reporter.generate(info)}"
     }

     case class Person(first:String, last:String, age:Int)
     case class Store(name:String, items:String*)
     case class Vehicle(model:String, year:Int)

     implicit object Person extends Reporter[Person]{
          def generate(person: Person): String ={
               s"Person ${person.first} ${person.last} with age ${person.age}"
          }
     }

     implicit object Store extends Reporter[Store]{
          def generate(store: Store): String ={
               s"Store ${store.name} has items: ${store.items.toVector.mkString(", ")}"
          }
     }

     implicit object Vehicle extends Reporter[Vehicle] {
          def generate(vehicle: Vehicle): String ={
               s"Vehicle ${vehicle.model} model made in ${vehicle.year}"
          }
     }


     def main(args: Array[String]) {

          val information = List(
               Person("Mary", "Jane", 22),
               Store("Slurps and Burps", "hair-dye", "moustache-grower", "tummy-ache fix"),
               Vehicle("Ferrari", 1934), Vehicle("Mazda", 1900), Vehicle("Ford", 1800),
               Person("Tyler", "Crosswood", 80),
               Store("Tim Hortons", "vanilla late", "chocolate cookies", "coffee")
          )

          val result = information.map {
               case t:Person => report(t)
               case t:Store => report(t)
               case t:Vehicle => report(t)
          }.mkString("\n")

          println(result)
     }
}
