package Books.Eckel_AtomicScala.classes.NamedAndDefaultArgs

/**
  *
  */
object Exercise5_Family {

     // note cannot default args for mom and dad because kids is variable *
     class Family(val mom: String, val dad: String, val kids: String*){
          def familySize = kids.length + 2
     }


     def main(args: Array[String]) {
          val fam = new Family(mom="Mom", dad="Dad", "Charlotte", "Daniel", "Martha")
          assert(fam.familySize == 5)
     }
}
