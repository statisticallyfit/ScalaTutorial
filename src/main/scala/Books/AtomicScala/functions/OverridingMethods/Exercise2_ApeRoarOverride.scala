package Books.AtomicScala.functions.OverridingMethods

/**
  *
  */
object Exercise2_ApeRoarOverride {



     class GreatApe(val weight: Double, val age: Int, val gender: String){
          def roar = Vector("Roar")
     }

     class Bonobo(weight:Double, age:Int, gender: String)
          extends GreatApe(weight, age, gender){
          override def roar = Vector("Roar", "Bonobo")
     }

     class Chimpanzee(weight:Double, age:Int, gender: String)
          extends GreatApe(weight, age, gender){
          override def roar = Vector("Roar", "Chimp")
     }

     class BonoboB(weight:Double, age:Int, gender: String)
          extends Bonobo(weight, age, gender){
          override def roar = Vector("Roar", "BonoboB")
     }

     class BonoboC(weight:Double, age:Int, gender: String)
          extends BonoboB(weight, age, gender){
          override def roar = Vector("Roar", "BonoboC")
     }


     def display(ape: GreatApe) = s"weight: ${ape.weight} age: ${ape.age} leader: ${ape.gender}"

     def main(args: Array[String]) {

          Console.println(new GreatApe(112, 9, "Male").roar)
          Console.println(new Bonobo(150, 14, "Female").roar)
          Console.println(new Chimpanzee(130, 18, "Female").roar)
          Console.println(new BonoboB(149, 13, "Male").roar)
          Console.println(new BonoboC(151, 15, "Male").roar)
     }
}
