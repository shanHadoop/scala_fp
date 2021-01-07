package day2

object ParserCombinator extends App {

  case class Parser[A](parse: String => List[(A, String)]) extends AnyVal {
  }

  case object Parser {

    def result[A]: A => Parser[A] = a => Parser(in => List((a, in)))

    def zero[A]: Parser[A] = Parser(_ => List())

    def item: Parser[Char] =
      Parser(
        in => in.headOption.fold(List.empty[(Char, String)])(ch => List((ch, in.tail)))
      )
  }

  println(Parser.item.parse("ShantanuKumar"))
  println(Parser.item.parse(""))
  println(Parser.zero.parse("shantanu"))
  println(Parser.result("Dutta").parse("Shantanu").head)

  //**************************************************************************************

//
//
//
//  // ********************
//  sealed trait Version
//
//  case class Major(int: Int) extends Version
//
//  case class Minor(int: Int) extends Version
//
//  case class Patch(int: Int) extends Version
//
//  val StripString =
//    """
//      |[Person]
//      |name=John
//      |age=35
//      |""".stripMargin
//
//  println(StripString)

}
