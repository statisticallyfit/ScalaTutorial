package Books.Eckel_AtomicScala.classes.ClassesAndObjects



object Exercise5_Family extends App {


     class Family(val mom:String, val dad:String, val kids:String*) {
          def familySize():Int = {
               var count = 2    // NOTE: This changed
               for(i <- kids) {
                    count = count + 1
               }
               count
          }
     }

     val family = new Family(mom="Mom", dad="Dad")
     assert(family.familySize == 2)
     assert(family.mom == "Mom")
     assert(family.dad == "Dad")
     assert(family.kids == Nil)
}