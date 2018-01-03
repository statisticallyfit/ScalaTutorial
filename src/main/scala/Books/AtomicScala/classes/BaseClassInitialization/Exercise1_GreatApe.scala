package Books.AtomicScala.classes.BaseClassInitialization

/**
  *
  */
object Exercise1_GreatApe {



     class GreatApe(val weight: Double, val age: Int, val clanLeader: Boolean)

     class Bonobo(weight:Double, age:Int, clanLeader:Boolean)
          extends GreatApe(weight, age, clanLeader)

     class Chimpanzee(weight:Double, age:Int, clanLeader:Boolean)
          extends GreatApe(weight, age, clanLeader)

     class BonoboB(weight:Double, age:Int, clanLeader:Boolean)
          extends Bonobo(weight, age, clanLeader)

     class BonoboC(weight:Double, age:Int, clanLeader:Boolean)
          extends BonoboB(weight, age, clanLeader)


     def display(ape: GreatApe) = s"weight: ${ape.weight} age: ${ape.age} leader: ${ape.clanLeader}"

     def main(args: Array[String]) {

          assert(display(new GreatApe(100,12, false)) == "weight: 100.0 age: 12 leader: false")
          assert(display(new Bonobo(100,12, true)) == "weight: 100.0 age: 12 leader: true")
          assert(display(new Chimpanzee(100,12, false)) == "weight: 100.0 age: 12 leader: false")
          assert(display(new BonoboB(100,12, true)) == "weight: 100.0 age: 12 leader: true")
          assert(display(new BonoboC(100,18, true)) == "weight: 100.0 age: 18 leader: true")
     }
}
