package Books.AtomicScala.classes.AbstractClasses

/**
  *
  */
object Exercise1_2_Animal {

     abstract class Animal {
          def templateMethod = s"The $animal goes $sound"
          def animal: String
          def sound: String
          def food: String
     }

     class Duck extends Animal {
          def animal = "Duck"
          def sound = "Quack"
          def food = "plants"
     }

     class Cow extends Animal {
          def animal = "Cow"
          def sound = "Moo"
          def food = "grass"
     }

     class Chicken extends Animal {
          def animal = "Chicken"
          def sound = "Cluck"
          def food = "insects"
     }

     class Pig extends Animal {
          def animal = "Pig"
          def sound = "Oink"
          def food = "anything"
     }

     def main(args: Array[String]) {
          val duck = new Duck
          val cow = new Cow
          val pig = new Pig
          val chicken = new Chicken

          Console.println(duck.templateMethod)
          Console.println(cow.templateMethod)
          Console.println(chicken.templateMethod)
          Console.println(pig.templateMethod)


          assert(duck.food == "plants")
          assert(cow.food == "grass")
          assert(chicken.food == "insects")
          assert(pig.food == "anything")
     }
}
