package Blogs.AdHocPolyBlogTryOuts.petExample

/**
  * NOTE: this approach is smelly because:
  *       1. functionality is divided between trait and typeclass
  *       2. nothing forces all Pet implementations to have a Rename instance! (we
  *           (had to specify both an upper bound and a context bound in esquire - too much)
  */
object TypeClass_SmellySuperTrait {


     trait Pet {
          def name: String
     }

     // the orthogonal typeclass to deal with the operation of Renaming
     trait Rename[A] {
          def rename(a: A, newName: String): A
     }



     case class Fish(name: String, age: Int) extends Pet

     object Fish {
          //defining the implicit instance
          implicit val FishRename = new Rename[Fish] {
               def rename(a: Fish, newName: String) = a.copy(name = newName)
          }
     }

     // note: the highlight: using implicit class to make rename()
     // act like a method as before. Any Pet wit a Rename instance
     // will automatically gain a rename METHOD by implicit conversion.
     implicit class RenameOps[A](a: A)(implicit ev: Rename[A]) {
          def rename(newName: String) = ev.rename(a, newName)
     }



     //says the type A must have a Pet and Rename instance
     def esquire[A <: Pet: Rename](a: A): A = a.rename(a.name + ", Esq.")


     def main(args: Array[String]) {
          val a = Fish("Jimmy", 2)
          val b = a.rename("Bob")

          Console.println(b)
          Console.println(esquire(a))
     }
}
