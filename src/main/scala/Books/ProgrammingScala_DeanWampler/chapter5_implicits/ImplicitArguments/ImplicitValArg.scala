package Books.ProgrammingScala_DeanWampler.chapter5_implicits.ImplicitArguments

/**
  *
  */
object ImplicitValArg {


     def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

     implicit val currentTaxRate = 0.08F

     def main(args: Array[String]) {
          Console.println(calcTax(50000F))
     }
}
