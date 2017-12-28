package Horstmann_ScalaImpatient.chapter5_classes

import scala.Predef._

/**
  *
  */
object Exercise2_BankAccount {

     class BankAccount(private var accountBalance: Double) {

          def deposit(amount: Double) = { accountBalance += amount }
          def withdraw(amount: Double) = {
               if (amount <= accountBalance){
                    accountBalance -= amount
               }
          }
          def balance = accountBalance
     }

     def main(args: Array[String]) {
          val bank = new BankAccount(100)
          bank.withdraw(90)
          assert(bank.balance == 10)
          bank.deposit(25)
          assert(bank.balance == 35)
     }
}
