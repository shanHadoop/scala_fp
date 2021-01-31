package day2

import day2.ParserCombinator.Parser.{bind, result}

object ParserCombinator extends App {

//  class Wrapper(val underlying: Int) extends AnyVal
//  It has a single, public val parameter that is the underlying runtime representation.
//    The type at compile time is Wrapper,
//  but at runtime, the representation is an INT

  case class Parser[A](parse: String => List[(A, String)]) extends AnyVal {
    def map[B](f:A => B):Parser[B] =bind(this)(a => result(f(a)))
    def flatMap[B](f:A=> Parser[B]):Parser[B] = bind(this)(f)
  }

  case object Parser {
    def result[A]: A => Parser[A] = a => Parser(in => List((a, in)))
    def zero[A]: Parser[A] = Parser(_ => List())
    def item: Parser[Char] =
      Parser(
        in => in.headOption.fold(List.empty[(Char, String)])(ch => List((ch, in.tail)))
      )

    // 2 seq , is like a combinator of two parser
    def seq[A,B]:Parser[A] => Parser[B] => Parser[(A,B)] =
      pa => pb => Parser[(A,B)](in => {
        val res1 = pa.parse(in)
          res1.headOption.fold(List.empty[((A,B),String)]){
            resPA => {
              val res2 = pb.parse(resPA._2)
              res2.headOption.fold(List.empty[((A,B),String)]){
                resPB => List(((resPA._1,resPB._1),resPB._2))
              }
            }
          }
        } )
   // bind just as flatmap
    def bind[A,B]: Parser[A] => (A=> Parser[B]) => Parser[B] =
      pa => f => Parser[B](in => {
        pa.parse(in).headOption.fold(List.empty[(B,String)]){
          t => f(t._1).parse(t._2)
        }
      })
  }

  def seq1[A,B]:Parser[A]=>Parser[B]=> Parser[(A,B)] =
    pa => pb => bind(pa)(a => bind(pb)(b => result((a,b))))


  // demo of item,zero,result :
  println(Parser.item.parse("ShantanuKumar")) // List((S,hantanuKumar))
  println(Parser.item.parse(""))   //List()
  println(Parser.zero.parse("shantanu"))   //List()
  println(Parser.result("Dutta").parse("Shantanu").head) //(Dutta,Shantanu)

  // demo of seq :
  import Parser._
  val combinedParser = Parser.seq(Parser.item)(Parser.item)
  println(combinedParser.parse("Shantanu Kumar")) //List(((S,h),antanu Kumar))
  val combinedParser1 = Parser.seq(item)(zero)
  println(combinedParser1.parse("Shantanu Kumar")) //List()   as it used Zero parser when second parser will be used it will make empty list
  val combinedParser2 = Parser.seq(item)(result(2))
  println(combinedParser2.parse("Shantanu Kumar"))  //List(((S,2),hantanu Kumar))






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
