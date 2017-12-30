package Books.Eckel_AtomicScala.classes.ClassExercises

/**
  *
  */
object Exercise1_Dimension {


     class Dimension(var height: Integer, var width: Integer)

     def main(args: Array[String]) {

          val d = new Dimension(5,7)
          assert(d.height == 5, "Test 1")
          assert(d.width == 7, "Test 2")
          d.height = 10
          d.width = 12
          assert(d.height == 10, "Test 3")
          assert(d.width == 12, "Test 4")
     }

}
