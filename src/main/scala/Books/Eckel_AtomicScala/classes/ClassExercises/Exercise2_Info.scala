package Books.Eckel_AtomicScala.classes.ClassExercises

/**
  *
  */
object Exercise2_Info {

     class Info(val name: String, var description: String)

     def main(args: Array[String]) {
          val info = new Info("stuff", "something")
          assert(info.name == "stuff")
          assert(info.description == "something")

          info.description = "something else"
          assert(info.description == "something else")
     }

}
