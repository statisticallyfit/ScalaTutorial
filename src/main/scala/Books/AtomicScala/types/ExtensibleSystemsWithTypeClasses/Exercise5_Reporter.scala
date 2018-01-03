package Books.AtomicScala.types.ExtensibleSystemsWithTypeClasses

/**
  *
  */
object Exercise5_Reporter {



     case class Person(first:String, last:String, age:Int)
     case class Store(name:String, items:String*)
     case class Vehicle(model:String, year:Int)



     trait Reporter[I] {
          def generate(info: I): String
     }
     // companion object
     object Reporter {
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
     }


     object Station {

          def report_ImplicitSyntax[I](info: I)(implicit personReporting: Reporter[I]): String ={
               s"Reporting: ${personReporting.generate(info)}"
          }

          def report_ContextBoundSyntax[I: Reporter](info: I) =
               s"Reporting: ${implicitly[Reporter[I]].generate(info)}"
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
               case t:Person => Station.report_ImplicitSyntax(t)
               case t:Store => Station.report_ContextBoundSyntax(t)
               case t:Vehicle => Station.report_ContextBoundSyntax(t)
          }.mkString("\n")

          println(result)


     }
}
