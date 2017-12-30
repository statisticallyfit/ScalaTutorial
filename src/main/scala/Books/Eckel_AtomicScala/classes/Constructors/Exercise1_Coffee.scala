package Books.Eckel_AtomicScala.classes.Constructors



object Exercise1_Coffee extends App {

     class Coffee(val shots: Int = 2,
                  val decaf: Int = 0,
                  /*val caf: Int = 0,*/
                  val milk: Boolean = false,
                  val toGo: Boolean = false,
                  val syrup: String = "") {

          var result = ""
          var caf = shots - decaf
          //println(shots, decaf, milk, toGo, syrup)

          def getCup(): Unit = {
               if (toGo) result += "ToGoCup "
               else result += "HereCup "
          }

          def pourShots(): Unit ={
               for (s <- 0 until caf){
                    result += "shot "
               }
               for (d <- 0 until decaf){
                    result += "decaf shot "
               }
          }

          def addMilk(): Unit ={ if (milk) result += "milk " }

          def addSyrup(): Unit ={ result += syrup }

          getCup()
          pourShots()
          addMilk()
          addSyrup()
     }


     val usual = new Coffee
     assert(usual.result == "HereCup shot shot ")
     val mocha = new Coffee(decaf=1, toGo = true, syrup = "Chocolate")
     assert(mocha.result == "ToGoCup shot decaf shot Chocolate")

     val doubleHalfCaf = new Coffee(shots=2, decaf=1)
     val tripleHalfCaf = new Coffee(shots=3, decaf=2)

     assert(doubleHalfCaf.result == "HereCup shot decaf shot ")
     assert(doubleHalfCaf.decaf == 1) //true
     assert(doubleHalfCaf.caf == 1)//true
     assert(doubleHalfCaf.shots == 2) //true

     assert(tripleHalfCaf.result == "HereCup shot decaf shot decaf shot ")
     assert(tripleHalfCaf.decaf == 2) //true
     assert(tripleHalfCaf.caf == 1) //true
     assert(tripleHalfCaf.shots == 3) //true*/
}