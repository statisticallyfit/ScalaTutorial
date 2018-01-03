package Books.ScalaForTheImpatient_CayHorstmann.chapter8_inheritance



import scala.Predef._

// help from: https://github.com/hempalex/scala-impatient/blob/master/Chapter08/02.scala


object Exercise1_2_BankAccount {

     class BankAccount(val initialBalance: Double) {

          private var balance = initialBalance

          def currentBalance = balance
          def deposit (amount: Double) = {balance += amount; balance }
          def withdraw (amount: Double) = {balance -= amount; balance }
          override def toString = "Balance = " + balance
     }


     class CheckingAccount (initialBalance: Double, val commission: Double = 1.0) extends BankAccount(initialBalance) {

          override def deposit (amount: Double) = super.deposit(amount - commission)
          override def withdraw (amount: Double) = super.withdraw(amount + commission)
     }




     class SavingsAccount (initialBalance: Double,
                           val yearlyInterestRate: Double = 0.10,
                           val freeTransactions: Int = 3,
                           val commission: Double = 1.0
                          ) extends BankAccount(initialBalance) {

          var transactionsInMonth: Int = 0

          def isFreeTransaction() = transactionsInMonth <= freeTransactions

          override  def deposit(amount: Double) = {
               transactionsInMonth += 1
               super.deposit(amount - (if (isFreeTransaction()) 0 else commission))
          }

          override def withdraw(amount: Double) = {
               transactionsInMonth += 1
               super.withdraw(amount + (if (isFreeTransaction()) 0 else commission))
          }

          def earnMonthlyInterest = {
               transactionsInMonth = 0 // reset to zero so you don't have to pay commission
               super.deposit(currentBalance * yearlyInterestRate / 12)
          }
     }

     def main(args: Array[String]) {
          println("\nBank account test")
          val bank = new BankAccount(100.0)
          bank.deposit(100); println(bank)
          bank.withdraw(90); println(bank)

          println("\nChecking account test")
          val checking = new CheckingAccount(100.0)
          checking.deposit(100); println(checking)
          checking.withdraw(90); println(checking)

          println("\nSavings account test")
          val save = new SavingsAccount(100, freeTransactions = 2, commission = 2.0)
          save.deposit(100); println(save)
          save.withdraw(100); println(save)
          save.deposit(100); println(save)
          save.withdraw(100); println(save)
          save.earnMonthlyInterest; println("Monthly interest: " + save)
          save.deposit(100); println(save)
          save.withdraw(100); println(save)
     }
}
