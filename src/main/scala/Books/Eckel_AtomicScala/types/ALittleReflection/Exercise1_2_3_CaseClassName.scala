package Books.Eckel_AtomicScala.types.ALittleReflection

import Books.Eckel_AtomicScala.classes.Polymorphism.Exercise1_2_3_4_5_6_FantasyGame.Name._

import scala.reflect.runtime.currentMirror

/**
  *
  */
object Exercise1_2_3_CaseClassName {

     object Name {
          def className(o: Any): String = {
               currentMirror.reflect(o).symbol.toString.replace('$', ' ').split(' ').last
          }
     }

     trait Name {
          override def toString: String = Name.className(this)
     }

     case class Toy(name:String, batteryOperated:Boolean)
     case class ReflectedToy(name:String, batteryOperated:Boolean) extends Name
     class Animal(name:String, mammal:Boolean)
     class ReflectedAnimal(name:String, mammal:Boolean) extends Name


     def main(args: Array[String]) {
          import scala.reflect.runtime.{universe=>ru}
          val currentMirror = ru.runtimeMirror(getClass.getClassLoader)

          val toy = new Toy("Teddy Bear", batteryOperated = false)
          val reflectedToy = new ReflectedToy("Bunny", batteryOperated = true)
          Console.println(toy)
          Console.println(reflectedToy)

          println()
          println(currentMirror.reflect(toy))
          println(currentMirror.reflect(toy).symbol.toString)
          println(currentMirror.reflect(toy).symbol.toString.replace('$', ' '))
          println()
          println(currentMirror.reflect(reflectedToy))
          println(currentMirror.reflect(reflectedToy).symbol.toString)
          println(currentMirror.reflect(reflectedToy).symbol.toString.replace('$', ' '))
          println()



          val animal = new Animal("giraffe", mammal = true)
          val reflectedAnimal = new ReflectedAnimal("panda", mammal = true)
          Console.println(animal)
          Console.println(reflectedAnimal)
          println()

          println(currentMirror.reflect(animal))
          println(currentMirror.reflect(animal).symbol.toString)
          println(currentMirror.reflect(animal).symbol.toString.replace('$', ' '))
          println()
          println(currentMirror.reflect(reflectedAnimal))
          println(currentMirror.reflect(reflectedAnimal).symbol.toString)
          println(currentMirror.reflect(reflectedAnimal).symbol.toString.replace('$', ' '))
     }
}
