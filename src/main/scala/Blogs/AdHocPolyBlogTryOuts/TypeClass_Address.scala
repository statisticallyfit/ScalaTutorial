package Blogs.AdHocPolyBlogTryOuts

/**
  *
  */
object TypeClass_Address {


     case class Address(num: Int, street: String, city: String, state: String, zip: String)


     trait LabelMaker[T] {
          def toLabel(value: T): String
     }
     // companion object to define semantics of the transformation Address --> LabelMaker
     object LabelMaker {
          implicit object AddressLabelMaker extends LabelMaker[Address] {
               def toLabel(adrs: Address): String ={
                    import adrs._ //we can import from a variable!
                    "%d %s, %s, %s - %s".format(num, street, city, state, zip)
               }
          }
     }


     object Display {
          def printLabel_ImplicitSyntax[T](t: T)(implicit lm: LabelMaker[T]) =
               lm.toLabel(t)

          def printLabel_ContextBoundSyntax[T: LabelMaker](t: T) =
               implicitly[LabelMaker[T]].toLabel(t)
     }


     def main(args: Array[String]) {
          Display.printLabel_ContextBoundSyntax(Address(100, "Monroe Street", "Denver", "CO", "80231"))
          Display.printLabel_ImplicitSyntax(Address(100, "Monroe Street", "Denver", "CO", "80231"))
     }
}
