package Books.AtomicScala.classes.Constructors



object Exercise_2_3_Tea extends App {

     class Tea(val name: String = "Earl Grey",
               val decaf: Boolean = false,
               val milk: Boolean = false,
               val sugar: Boolean = false,
               val teaBags: Int = 1) {

          def extras(): String = {
               var text: String = ""
               if (decaf) text += " decaf"
               if (milk) text += " + milk"
               if (sugar) text += " + sugar"
               text
          }

          def calories(): Int = {
               var total: Int = 0
               if (milk) total += 100
               if (sugar) total += 16
               total
          }

          def describe(): String ={
               if (teaBags == 1) name + " (" + teaBags + " bag)" + extras
               else if (teaBags > 1) name + " (" + teaBags + " bags)" + extras
               else name + " (--- bags)" + extras
          }
     }

     val tea = new Tea
     println(tea.describe())
     println(tea.describe == "Earl Grey (1 bag)")
     println(tea.calories == 0)
     println(!tea.sugar)

     val lemonZinger = new Tea(teaBags = 2, decaf = true, name = "Lemon Zinger")
     println(lemonZinger.describe())
     println(lemonZinger.describe == "Lemon Zinger (2 bags) decaf")
     println(lemonZinger.calories == 0)
     println(!lemonZinger.sugar)

     val sweetGreen = new Tea(name = "Jasmine Green", sugar = true)
     println(sweetGreen.describe())
     println(sweetGreen.describe() == "Jasmine Green (1 bag) + sugar")
     println(sweetGreen.calories() == 16)
     println(sweetGreen.sugar)

     val teaLatte = new Tea(sugar = true, milk = true)
     println(teaLatte.describe())
     println(teaLatte.describe() == "Earl Grey (1 bag) + milk + sugar")
     println(teaLatte.calories() == 116)
     println(teaLatte.sugar)
}