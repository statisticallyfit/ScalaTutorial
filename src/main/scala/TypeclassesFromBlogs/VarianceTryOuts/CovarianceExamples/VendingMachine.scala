//
//package TEMP.VarianceTryOuts.CovarianceExamples
//
///**
//  *
//  */
//
//
//class Drink                        { override def toString = "Drink"}
//class SoftDrink extends Drink      { override def toString = "SoftDrink"}
//class Cola extends SoftDrink       { override def toString = "Cola"}
//class TonicWater extends SoftDrink { override def toString = "TonicWater"}
//class Juice extends Drink          { override def toString = "Juice"}
//class OrangeJuice extends Juice    { override def toString = "OrangeJuice"}
//class AppleJuice extends Juice     { override def toString = "AppleJuice"}
//
//
//class VendingMachine[+A <: Drink](private[this] var currentItem: Option[A],
//                                  private[this] var items: List[A]) {
//
//     //look at highlight in mbonaci T- --- meaning?
//     // note how to fix this to eb stateless?
//     if (currentItem.isEmpty && items != Nil){
//          currentItem = Some(items.head)
//          items = items.tail
//     }
//     def this(items: List[A]) = this(None, items)
//
//     /*def dispenseNext(): VendingMachine[A] = {
//          new Dispenser[A](currentItem, items).dispenseNext()
//     }*/
//     def dispenseNext(): VendingMachine[A] = {
//          items match {
//               case Nil => {
//                    if(currentItem.isDefined) {
//                         val result = new VendingMachine(currentItem, Nil)
//                         currentItem = None
//                         result
//                    }
//                    else new VendingMachine(None, Nil)
//               }
//               case t :: ts => {
//                    /*if(currentItem.isEmpty && items != Nil){
//                         return new VendingMachine(Some(t), ts)
//                    }*/
//                    val result = new VendingMachine(currentItem, t :: ts)
//                    currentItem = Some(t)
//                    items = ts
//                    result
//               }
//          }
//     }
//
//     def addAll[B >: A](newItems: List[B]): VendingMachine[B] = {
//          new VendingMachine[B](items ++ newItems)
//     }
//
//     override def toString = s"$currentItem :: ${items.mkString(", ")}"
//}
//
//
//class Dispenser[A](val currIt: Option[A], its: List[A]) {
//     private var currentItem = currIt
//     private var items = its
//
//     if (currIt.isEmpty && its != Nil){
//          currentItem = Some(its.head)
//          items = its.tail
//     }
//
//     def dispenseNext(): VendingMachine[A] = {
//          items match {
//               case Nil => {
//                    if(currentItem.isDefined) {
//                         val result = new VendingMachine(currentItem, Nil)
//                         currentItem = None
//                         result
//                    }
//                    else new VendingMachine(None, Nil)
//               }
//               case t :: ts => {
//                    val result = new VendingMachine(currentItem, t :: ts)
//                    currentItem = Some(t)
//                    items = ts
//                    result
//               }
//          }
//     }
//}
//
//
//
//
//
//object VendingMachineRunner extends App {
//
//     /* covariance */
//     var colasVM: VendingMachine[Cola] = new VendingMachine(List(new Cola, new Cola))
//     var softDrinksVM: VendingMachine[SoftDrink] = colasVM.addAll(List(new TonicWater, new Cola))
//
//     //softDrinksVM = colasVM
//
//
//     //(1 to 3).foreach(i => println(softDrinksVM.dispenseNext()))
//     println(softDrinksVM.dispenseNext())
//     println(softDrinksVM.dispenseNext())
//     println(softDrinksVM.dispenseNext())
//     println(softDrinksVM.dispenseNext())
//
//
//}
//
