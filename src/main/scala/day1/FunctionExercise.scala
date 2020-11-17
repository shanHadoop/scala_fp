package day1

object FunctionsExercise extends App {
  //practicing HoF which parametric polymorphic
  //excersise 1 def tupled[T1, T2, R](f: (T1, T2) => R): ((T1, T2)) => R = ???
  def tupled[T1, T2, R](f: (T1, T2) => R): ((T1, T2)) => R =
    input => f(input._1, input._2)
  val addTupleElements: Tuple2[Int, Int] => Int = t => t._1 + t._2
  println(s"excersise 1-> tupled = " + addTupleElements(10, 20))

  //excersise 2
  //def untupled[T1, T2, R](f: ((T1, T2)) => R): (T1, T2) => R = ???
  def untupled[T1, T2, R](f: ((T1, T2)) => R): (T1, T2) => R = (val1, val2) => f((val1, val2))
  val addTupleElements1: (Int, Int) => Int = (a, b) => addTupleElements((a, b))
  println(s"excersise 2-> untupled = " + addTupleElements1(10, 20))

  //excersise 3
  // def makeCurried[A,B,C](f:(A,B) => C): A => B => C = ???
  def makeCurried[A, B, C](f: (A, B) => C): A => B => C = valueA => valueB => f(valueA, valueB)
  def addCurFunc(a: Int, b: Int): Int = a + b
  println(s"excersise 3-> makeCurried = " + makeCurried(addCurFunc)(10)(20))

  //excersise 4
  //def uncurry[A,B,C](f:A => B => C): (A, B) => C = ???
  def uncurry[A, B, C](f: A => B => C): (A, B) => C = (valueA, valueB) => f(valueA)(valueB)
  def addUnCurFunc(a: Int)(b: Int): Int = a + b
  println(s"excersise 4-> addUnCurFunc = " + uncurry(addUnCurFunc)(10, 20))


  // excercise 5
  // def flipArgs[A,B,C](f:A => B => C): B => A => C = ???
  def flipArgs[A, B, C](f: A => B => C): B => A => C = valueA => valueB => f(valueB)(valueA)
  def addflipArgs(a: String)(b: String): String = a + b
  println(s"excersise 5-> flipArgs = " + flipArgs(addflipArgs)("Shantanu")("Dutta"))

  // exercise 6
  //def fanOut[C, A, B](fst: C => A, snd: C => B): C => (A, B) = ???
  def fanOut[C, A, B](fst: C => A, snd: C => B): C => (A, B) = valueC => (fst(valueC), snd(valueC))
  def fNameUpper = (s: String) => s.toUpperCase
  def fReverse = (s: String) => s.reverse
  println(s"excersise 6-> fanOut = " + fanOut(fNameUpper, fReverse)("Shantanu"))

  //exercise 7
  // def fanIn[C, A, B](h: C => (A, B)): (C => A, C => B) = ???
  def fanIn[C, A, B](h: C => (A, B)): (C => A, C => B) =  (h(_)._1,h(_)._2)
  def spitIntoTuple(str:String) = (str.charAt(1),str.contains(str.charAt(1)))
  println(s"excersise 7-> fanIn = " + )
  //exercise 8
  // def bimap[A, A1, B, B1](f: A => A1, g: B => B1): ((A, B)) => (A1, B1) = ???
  def bimap[A, A1, B, B1](f: A => A1, g: B => B1): ((A, B)) => (A1, B1) = ???

  //exercise 9
  // def either[C, A, B](f: A => C, g: B => C): Either[A, B] => C = ???
  def either[C, A, B](f: A => C, g: B => C): Either[A, B] => C = {
    case Left(l) => f(l)
    case Right(r) => g(r)
  }
  println(s"excersise 9-> either = " + either(fNameUpper, fReverse)(Right("Shantanu"))) // reverse
  println(s"excersise 9-> either = " + either(fNameUpper, fReverse)(Left("Shantanu"))) // Uppercase

  //Ecercise 10
  // def chain[T](fs:Seq[T => T]):T => T = ???
  def chain[T](fs: Seq[T => T]): T => T = t => fs.foldLeft(t)((a, op) => op(a))

  println(s"excersise 10-> chain 1 = " + chain[Int](Seq({_ + 10}, {_ + 10}))(10))
  println(s"excersise 10-> chain 2= " + chain[String](Seq({_ + "Second"}, {_ + "Third"}))("First"))
  def serviceCharge: Double => Double = a => a + (a * (10D / 100))
  def VAT: Double => Double = a => a + (a * (5D / 100))
  println(chain[Double](Seq(serviceCharge, VAT))(1000D))


}
