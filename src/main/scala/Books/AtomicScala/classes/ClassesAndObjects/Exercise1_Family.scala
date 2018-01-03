package Books.AtomicScala.classes.ClassesAndObjects



object Exercise1_Family extends  App {

     class Family (familyNames: String*) {
          def familySize = familyNames.length
     }

     val family1 = new Family("Mom", "Dad", "Sally", "Dick")
     assert(family1.familySize == 4)

     val family2 = new Family("Dad", "Mom", "Harry")
     assert(family2.familySize == 3)
}