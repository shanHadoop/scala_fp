package day2

object TypeClassExercise extends App{
  //Ad-hoc polymorphism and OCP

  //Things required to implement the pattern in scala
  /*
  * 1. Behaviour
  * 2. Laws/properties
  * 3. instance on a Type
  * 4. interface methods
  * */

  /*
  *  Exercise 1
  *  Create a type class PrettyPrint, which prints any type in a fancy way.
  *
  * */
trait PrettyPrint[A] {
  def getFancyPrint(a:A):String ;
}
  val stringprettyPrint: PrettyPrint[String] =
    new PrettyPrint[String] {
      def getFancyPrint(int: String): String = s"String $int"
    }
  val intprettyPrint: PrettyPrint[Int] =
    new PrettyPrint[Int] {
      def getFancyPrint(int: Int): String = s"int $int"
    }

  val longprettyPrint:PrettyPrint[Long] =
    new PrettyPrint[Long] {
    def getFancyPrint(mylong:Long):String = s"long $mylong"
  }

  val listprettyPrint:PrettyPrint[List[String]] =
    new PrettyPrint[List[String]] {
      override def getFancyPrint( a:List[String]) : String = s"List ${a.mkString(",")}"
    }


  println(listprettyPrint.getFancyPrint(List("Shantanu","Dutta","KUMAR")))
  println(stringprettyPrint.getFancyPrint("Shantanu Kumar Dutta"))


  /*
  * Exercise 2
  * Create a type class Enum
  * */
  trait Enum[T]
}
