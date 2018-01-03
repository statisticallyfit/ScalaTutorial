package Books.AtomicScala.classes.BaseClassInitialization

/**
  *
  */
object Exercise3_HomeHeart {


     class House(val address:String, val state:String, val zip: String){

          def this(state:String, zip:String) = this("?", state,zip)
          def this(zip:String) = this("?", "?", zip)
     }

     // note uses constructor 1
     class Home(address:String, state:String, zip:String, val name:String, val heart:Boolean=true)
          extends House(address, state, zip) {
          //override def toString = s"$name: $address, $state $zip"
          override def toString = "where the heart is"
     }

     // note uses constructor 2
     class VacationHouse(state:String, zip:String, val startMonth:Int, val endMonth:Int)
          extends House(state, zip)

     // note uses constructor 3
     class TreeHouse(val name: String, zip:String)
          extends House(zip)


     def main(args: Array[String]) {

          val home = new Home("123 Main", "FL", "99999", "The retreat", true)
          assert(home.toString == "where the heart is")
          assert(home.heart)
     }
}
