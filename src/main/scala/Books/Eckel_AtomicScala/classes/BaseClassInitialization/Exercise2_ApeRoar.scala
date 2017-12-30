package Books.Eckel_AtomicScala.classes.BaseClassInitialization

/**
  *
  */
object Exercise2_ApeRoar {



     class GreatApe(val weight: Double, val age: Int, val clanLeader: Boolean){
          def roar() = Vector("Roar")
     }

     class Bonobo(weight:Double, age:Int, clanLeader:Boolean)
          extends GreatApe(weight, age, clanLeader){
          println(roar() + " from a bonobo")
     }

     class Chimpanzee(weight:Double, age:Int, clanLeader:Boolean)
          extends GreatApe(weight, age, clanLeader)

     class BonoboB(weight:Double, age:Int, clanLeader:Boolean)
          extends Bonobo(weight, age, clanLeader)

     class BonoboC(weight:Double, age:Int, clanLeader:Boolean)
          extends BonoboB(weight, age, clanLeader)


     def display(ape: GreatApe) = s"weight: ${ape.weight} age: ${ape.age} leader: ${ape.clanLeader}"

     def main(args: Array[String]) {

          new Bonobo(150, 14, false)
     }
}
