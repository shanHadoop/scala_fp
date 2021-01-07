/*
package day1

import java.util.Date




Sealed trait AccountType
case object Checking extends AccountType
case object Saving extends AccountType

trait AccountRepository

trait AccountService1[Account,Amount,Balance] {
def Open(no:String,
         name:String,
         OpeningDate:Option[Date],
         accountType: AccountType,
         repository:AccountRepository):Future[Account]

def close(no:String,
          closingDate:Option[Date],
          repo:AccountRepository):Future[Account]

  def credit(no: String,
             amount: Amount,
             repository: AccountRepository): Future[Account]
}


//version 2, identify the repetition pattern in parameter and lets curry them
trait AccountService2[Account,Amount,Balance] {
  def Open(no:String,
           name:String,
           OpeningDate:Option[Date],
           accountType: AccountType):AccountRepository => Future[Account]

  def close(no:String,
            closingDate:Option[Date]):AccountRepository => Future[Account]

  def credit(no:String,amount:Amount):AccountRepository => Future[Account]
}



//version 3, that sure looks an external resource needs to be injected
// lets as an intermediate step give a type alias
trait AccountService3[Account, Amount, Balance] {
type TakeAccountRepoAndGiveFutureOf[A] = AccountRepository => Future[A]

//if you want to use Cat library for the same type class as
//type TakeAccountRepoAndGiveFutureOf[A] = Kleisli[Option,AccountRepository,A]
// Syntax :- final case class Kleisli[F[_], -A, B](run: A => F[B])
  def Open(no:String,
           name:String,
           OpeningDate:Option[Date],
           accountType: AccountType):TakeAccountRepoAndGiveFutureOf[Account]

  def close(no:String,
            closingDate:Option[Date]):TakeAccountRepoAndGiveFutureOf[Account]

  def credit(no:String,amount:Amount):TakeAccountRepoAndGiveFutureOf[Account]
  def balance(no: String): TakeAccountRepoAndGiveFutureOf[Balance]
}

*/
