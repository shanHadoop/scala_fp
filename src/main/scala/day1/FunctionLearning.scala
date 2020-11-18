package day1

import day1.FunctionLearning.TaxIN.{ServiceChargeTaxIN, ServiceTaxIN, VATIN}

object FunctionLearning extends App {
  def fun1(a: Int, b: String) = {
    a + " " + b
  }

  def fun(a: String, b: String): String = {
    a + " " + b
  }
  //val f : A => B => C
  //val f : Function1[A, Function1[B, C]]

  // funtions are expression and they have a value
  println(fun1(1, "Hello"))
  val paramLess: (Int => Int) = x => x + 10;

  // loop -- avoid looping control use tail recursion
  def repeat(str: String, nTime: Int): String = nTime match {
    case 0 => ""
    case 1 => str
    case _ => str + repeat(str, nTime - 1)
  }

  println(repeat("Shantanu ", 1))

  def isPrime(n: Int) = {
    def go(num: Int, isPrimeNum: Boolean): Boolean = {
      if (num <= 1) isPrimeNum
      else go((num - 1), ((n % num != 0) && isPrimeNum))
    }

    go(n / 2, true)
  }

  val num = 7
  println(s"num 7 is ${isPrime(7)}")
  println(s"num 8 is ${isPrime(8)}")
  println(s"num 21 is ${isPrime(21)}")

  // HOF
  val ls = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  val addTwo = (x: Int) => x + 2
  ls.map(addTwo)
  println(ls)


  trait TaxIN {
    val applyTAXFormulate: Double => Double;
  }

  object TaxIN {

    case class VATIN(applyTAXFormulate: Double => Double = _ * .30) extends TaxIN
    case class ServiceTaxIN(applyTAXFormulate: Double => Double = _ * .20) extends TaxIN
    case class ServiceChargeTaxIN(applyTAXFormulate: Double => Double) extends TaxIN

  }
  // (f compose g)(x) = f(g(x)). The second function g(x) is ran first
  // and its result is passed along to the function f(x).
  //  Ordering using andThen: f(x) andThen g(x) = g(f(x))
  //  Ordering using compose: f(x) compose g(x) = f(g(x))

  val addAllTax: TaxIN => Double => Double = tax => amount => tax.applyTAXFormulate(amount) + amount
  val addAllTax_withComposit: Double => Double = addAllTax(ServiceChargeTaxIN(_ * .40)).
    compose(addAllTax(ServiceTaxIN())).
    compose(addAllTax(VATIN()))

    println(addAllTax_withComposit(10000))


  import scala.util.chaining._
  val addTaxOnBillTapandPipe: Double => Double = amt =>
    amt.pipe(addAllTax(VATIN()))
      .tap(_ => s"This is not going to print...")
       .tap(a=>println(s"after VATIN $a"))
      .pipe(addAllTax(ServiceTaxIN())).
      tap(a => println(s"after ServiceTaxIN $a"))
      .pipe(addAllTax(ServiceChargeTaxIN(_ * .4D))).
      tap(a => println(s"after ServiceChargeTaxIN $a"))

  println(addTaxOnBillTapandPipe(10000))

type myfunc[A,B] = A => B
trait myFunction1[A,B] extends (A => B)
case class FunctionWrapper[A,B](f:A => B)


}
