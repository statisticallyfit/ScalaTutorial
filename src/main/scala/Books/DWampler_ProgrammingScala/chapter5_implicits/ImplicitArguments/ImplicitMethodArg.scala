package Books.DWampler_ProgrammingScala.chapter5_implicits.ImplicitArguments

/**
  *
  */
object ImplicitMethodArg {

     def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

     //using method because we need to tax based on location, holidays....

     object SimpleStateSalesTax {
          implicit val rate: Float = 0.05F
     }

     case class ComplicatedSalesTaxData(baseRate:Float, isTaxHoliday:Boolean, storeID:Int)
     object ComplicatedSalesTax {
          private def extraTaxRateForStore(id:Int):Float ={
               //from id, determine location, then extra taxes
               if(id % 3 == 0) 0.02F
               else if(id % 5 == 0) 0.15F
               else 0.04F
          }

          implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float ={
               if(cstd.isTaxHoliday) 0.0F
               else cstd.baseRate + extraTaxRateForStore(cstd.storeID)
          }
     }



     def main(args: Array[String]) {

          {
               import SimpleStateSalesTax.rate
               val amount = 100F
               Console.println(s"Tax on $amount = ${calcTax(amount)}")
               assert(calcTax(amount) == 5.0)
          }

          {
               import ComplicatedSalesTax.rate
               implicit val myStore = ComplicatedSalesTaxData(0.06F, isTaxHoliday = false, 1010)
               val amount = 100F
               Console.println(s"Tax on $amount = ${calcTax(amount)}")
               assert(calcTax(amount) == 8.0)
          }
     }
}
