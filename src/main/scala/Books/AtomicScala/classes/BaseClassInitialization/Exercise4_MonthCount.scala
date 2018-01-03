package Books.AtomicScala.classes.BaseClassInitialization

/**
  *
  */
object Exercise4_MonthCount {


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
          extends House(state, zip){

          override def toString = s"Rented house in $state for months of " +
               s"${new Month(startMonth).month()} through ${new Month(endMonth).month()}."
     }

     // note uses constructor 3
     class TreeHouse(val name: String, zip:String)
          extends House(zip)



     case class Month(monthNum: Int){
          def month(): String ={
               monthNum match {
                    case 1 => "January"
                    case 2 => "February"
                    case 3 => "March"
                    case 4 => "April"
                    case 5 => "May"
                    case 6 => "June"
                    case 7 => "July"
                    case 8 => "August"
                    case 9 => "September"
                    case 10 => "October"
                    case 11 => "November"
                    case 12 => "December"
               }
          }
     }


     def main(args: Array[String]) {

          val home = new VacationHouse("AK", "48713", 6, 8)
          println(home)
          assert(home.toString == "Rented house in AK for months of June through August.")
     }
}
