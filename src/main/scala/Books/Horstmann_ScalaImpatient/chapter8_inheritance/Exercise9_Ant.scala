package Books.Horstmann_ScalaImpatient.chapter8_inheritance

/**
  *
  */
object Exercise9_Ant {

     // todo: what's supposed to happen when replacing val sightRange with
     // todo: def sightRange all over the place?

     class Creature {
          def sightRange: Int = 10
          val environment: Array[Int] = new Array[Int](sightRange)
     }


     class Ant extends Creature {
          override def sightRange = 2
     }
}
