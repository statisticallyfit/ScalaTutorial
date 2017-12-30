package Books.Eckel_AtomicScala.classes.NamedAndDefaultArgs

/**
  *
  */
object Exercise3_4_Planet {


     class Planet(name: String, description: String, moons: Int = 1){
          def hasMoon: Boolean = moons > 0
     }

     def main(args: Array[String]) {
          val mercury = new Planet("Mercury", "small and hot planet", 0)
          assert(!mercury.hasMoon)

          val earth = new Planet(moons = 1, name = "Earth", description = "a hospitable planet")
          assert(earth.hasMoon)
     }
}
