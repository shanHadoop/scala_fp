package day1

import scala.annotation.tailrec

object FunctionsExercise extends App {
  //practicing HoF which parametric polymorphic
  //excersise 1 def tupled[T1, T2, R](f: (T1, T2) => R): ((T1, T2)) => R = ???
  def tupled [ T1, T2, R ] ( f : (T1, T2) => R ) : ((T1, T2)) => R =
    input => f ( input._1, input._2 )

  val addTupleElements : Tuple2 [ Int, Int ] => Int = t => t._1 + t._2
  println ( s"excersise 1-> tupled = " + addTupleElements ( 10, 20 ) )

  //excersise 2
  //def untupled[T1, T2, R](f: ((T1, T2)) => R): (T1, T2) => R = ???
  def untupled [ T1, T2, R ] ( f : ((T1, T2)) => R ) : (T1, T2) => R = ( val1, val2 ) => f ( (val1, val2) )

  val addTupleElements1 : (Int, Int) => Int = ( a, b ) => addTupleElements ( (a, b) )
  println ( s"excersise 2-> untupled = " + addTupleElements1 ( 10, 20 ) )

  //excersise 3
  // def makeCurried[A,B,C](f:(A,B) => C): A => B => C = ???
  def makeCurried [ A, B, C ] ( f : (A, B) => C ) : A => B => C = valueA => valueB => f ( valueA, valueB )

  def addCurFunc ( a : Int, b : Int ) : Int = a + b

  println ( s"excersise 3-> makeCurried = " + makeCurried ( addCurFunc )( 10 )( 20 ) )

  //excersise 4
  //def uncurry[A,B,C](f:A => B => C): (A, B) => C = ???
  def uncurry [ A, B, C ] ( f : A => B => C ) : (A, B) => C = ( valueA, valueB ) => f ( valueA )( valueB )

  def addUnCurFunc ( a : Int )( b : Int ) : Int = a + b

  println ( s"excersise 4-> addUnCurFunc = " + uncurry ( addUnCurFunc )( 10, 20 ) )


  // excercise 5
  // def flipArgs[A,B,C](f:A => B => C): B => A => C = ???
  def flipArgs [ A, B, C ] ( f : A => B => C ) : B => A => C = valueA => valueB => f ( valueB )( valueA )

  def addflipArgs ( a : String )( b : String ) : String = a + b

  println ( s"excersise 5-> flipArgs = " + flipArgs ( addflipArgs )( "Shantanu" )( "Dutta" ) )

  // exercise 6
  //def fanOut[C, A, B](fst: C => A, snd: C => B): C => (A, B) = ???
  def fanOut [ C, A, B ] ( fst : C => A, snd : C => B ) : C => (A, B) = valueC => (fst ( valueC ), snd ( valueC ))

  def fNameUpper = ( s : String ) => s.toUpperCase

  def fReverse = ( s : String ) => s.reverse

  println ( s"excersise 6-> fanOut = " + fanOut ( fNameUpper, fReverse )( "Shantanu" ) )

  //exercise 7
  //def fanIn[C, A, B](h: C => (A, B)): (C => A, C => B) = ???
  //  def fanIn1 [ C, A, B ] ( h : C => (A, B) ) : (C => A, C => B) = (c => h ( c )._1, c => h ( c )._2)
  def fanIn [ C, A, B ] ( h : C => (A, B) ) : (C => A, C => B) = (h ( _ )._1, h ( _ )._2)

  val oddandeven : List [ Int ] => (List [ Int ], List [ Int ]) =
    myList => (myList.filter ( _ % 2 == 0 ), myList.filter ( _ % 2 != 0 ))
  val (evenls, oddls) = fanIn ( oddandeven )
  println ( s"excersise 7-> fanIn = evens " + evenls ( List ( 1, 2, 3, 45, 6, 7, 9, 10, 16 ) ) )
  println ( s"excersise 7-> fanIn = odd " + oddls ( List ( 1, 2, 3, 45, 6, 7, 9, 10, 16 ) ) )


  //  exercise 8
  //   def bimap[A, A1, B, B1](f: A => A1, g: B => B1): ((A, B)) => (A1, B1) = ???
  def bimap [ A, A1, B, B1 ] ( f : A => A1, g : B => B1 ) : ((A, B)) => (A1, B1) =
    valueTupleAB => (f ( valueTupleAB._1 ), g ( valueTupleAB._2 ))

  println ( s"excersise 8-> bimap = " + bimap ( fNameUpper, fReverse )( ("Shantanu", "Dutta") ) )


  //exercise 9
  // def either[C, A, B](f: A => C, g: B => C): Either[A, B] => C = ???
  def either [ C, A, B ] ( f : A => C, g : B => C ) : Either [ A, B ] => C = {
    case Left ( l ) => f ( l )
    case Right ( r ) => g ( r )
  }

  println ( s"excersise 9-> either = " + either ( fNameUpper, fReverse )( Right ( "Shantanu" ) ) ) // reverse
  println ( s"excersise 9-> either = " + either ( fNameUpper, fReverse )( Left ( "Shantanu" ) ) ) // Uppercase

  //Ecercise 10
  // def chain[T](fs:Seq[T => T]):T => T = ???
  def chain [ T ] ( fs : Seq [ T => T ] ) : T => T = t => fs.foldLeft ( t )( ( a, op ) => op ( a ) )

  println ( s"excersise 10-> chain 1 = " + chain [ Int ]( Seq ( {
    _ + 10
  }, {
    _ + 10
  } ) )( 10 ) )
  println ( s"excersise 10-> chain 2= " + chain [ String ]( Seq ( {
    _ + "Second"
  }, {
    _ + "Third"
  } ) )( "First" ) )

  def serviceCharge : Double => Double = a => a + (a * (10D / 100))

  def VAT : Double => Double = a => a + (a * (5D / 100))

  println ( chain [ Double ]( Seq ( serviceCharge, VAT ) )( 1000D ) )


  //Pure functions & side effects introduction
  /*
  * 1. It always returns a value
  *   a. Always returns same value for the same input
  * 2. No side effect (throwing exception, changing something which is global, latency, I/O, etc)
  *   b. It manages effect
  *
  * Why pure functions
  * */

  //Simple recursion examples along with a recursive data structure
  sealed trait MyList [ +A ] // nonEmpty(Cons(head, tail)) / empty
  case class Cons [ A ] ( head : A, tail : MyList [ A ] ) extends MyList [ A ]
  case object Empty extends MyList [ Nothing ]

  def add[A](element: A,mylst:MyList[A]): MyList[A] = new Cons(element, mylst)


  def ++[A](list: MyList[A],thisList:MyList[A]): MyList[A] = list match {
    case Cons(h,t) => new Cons(h, ++ (t,thisList))
  }
  def head [ A ] ( list : MyList [ A ] ) : Option [ A ] = list match {
    case Empty => None
    case Cons ( h, _ ) => Some ( h )
  }

  def tail [ A ] ( list : MyList [ A ] ) : Option [ MyList [ A ] ] = list match {
    case Empty => None
    case Cons ( _, t ) => Some ( t )
  }

  def length [ A ] ( list : MyList [ A ] ) : Int = {
    @tailrec
    def loop ( l : MyList [ A ], acc : Int ) : Int = l match {
      case Empty => acc
      case Cons ( _, t ) => loop ( t, acc + 1 )
    }

    def loop1 ( l : MyList [ A ] ) : Int = l match {
      case Empty => 0
      case Cons ( _, t ) => 1 + loop1 ( t )
    }

    loop ( list, 0 )
  }

  /*
  * Exercise 2
  * implement method take for list
  * */
  def take [ A ] ( i : Int )( list : MyList [ A ] ) : MyList [ A ] = if(i==0) Empty else
  {
    @tailrec
    def doLoop(num:Int,list:MyList[A], accList:MyList[A]):MyList[A] ={
      list match {
        case Empty => Empty
        case _ if num == 0  => accList
        case Cons(h,t) => doLoop(num -1 , t, Cons(h,accList))
      }
  }
    doLoop(i,list,Empty)

  }


//  def take1 [ A ] ( i : Int )( list : MyList [ A ] ) : MyList [ A ] =
//      i match {
//    case 0 =>  Empty
//    case x if x < length(list) =>  {
//    var newList:MyList[A] = Empty
//
//      @tailrec
//    def doLoop(len:Int,acc:MyList[A]):MyList[A] ={
//      len match {
//        case 0 => acc
//        case _ => {
//          val h = head(list).get
//          val t = tail(list).getOrElse(Empty)
//          newList = Cons(h,acc)
//          doLoop(len -1 , t)
//        }
//      }
//    }
//  doLoop(i,newList)
//
//    }
//  }


  /*
  * Exercise 3
  * implement method map for the list
  * */
//  // Without tail recurssion
//  def map[A,B](f:A => B)(list: MyList[A]):MyList[B] = list match {
//    case Empty => Empty
//    case Cons ( h, t ) =>   new Cons(f(h), map(f)(t))
//  }

  // With tail recurssion
  def map[A,B](f:A => B)(list: MyList[A]):MyList[B] = {
@tailrec
    def doLoop(list:MyList[A],accuList:MyList[B]):MyList[B] = {
      list match {
        case Empty => accuList
        case Cons(h, t) => doLoop(t,Cons(f(h),accuList) )
      }
    }
    doLoop(list,Empty)
  }

//  def flatMap[A,B](f:A => B)(list: MyList[MyList[A]]):MyList[B] = list match {
//    case Empty => Empty
//    case Cons ( h, t ) =>   new Cons(f(h), map(f)(t))
//  }


  /*
  * Exercise 4
  * implement a method fold on scala list
  * */
  def fold[A,B](list: MyList[A])(zero:B)(f:(A, B) => B):B = {
@tailrec
    def doLoop(l: MyList[A])(initial: B)(f: (A, B) => B):B = {
      l match {
        case Empty => initial
        case Cons(h, t) =>doLoop(t)(f(h,initial))(f)
      }
    }
  doLoop(list)(zero)(f)
  }

  /*
  * Exercise 5
  * implement a method flatten to flatten a list
  *
  * introducing generalised types so early just to give you a taste of scala
  * https://blog.bruchez.name/2015/11/generalized-type-constraints-in-scala.html
  * */

//  def ++[A](list: MyList[A],thisList:MyList[A]): MyList[A] = list match {
//    case Cons(h,t) => new Cons(h, ++ (t,thisList))
//  }
  // =:=   |  Type1 ==:== Type2  , Make sure that Type1 is exactly the same as Type2, or else report an error.

  //  <:<   |  Type1 <:< Type2 , Make sure that Type1 is a subtype of Type2, or else report an error.

  def flatten[A, B](l:List[A])(implicit ev: A <:< List[B]):List[B]= {


    def loopDo[B](l1: List[A], accList: List[B]): List[B] = l1 match {
      case Nil => accList
      case (head: List[B]) :: tail => loopDo(tail, accList ++ head)
      //  }
    }
    loopDo(l,List.empty[B])
  }


  /*A bit of implicits*/
  //https://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html

  /*
  * 1. implicit parameter
  * 2. implicit conversion, not advised to be used
  * 4. Implicit class, extension methods
  * 3. context bounds
  * 5. Implicit errors annotation @implicitNotFound
  * */


  val myList = Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Cons(6,Empty))))))
  val myLis1 = Cons("A",Cons("B",Empty))
  val myLis2 = Cons("C",Cons("D",Empty))
  val myLis3 = Cons("C",Cons("D",Empty))
  val mainList = Cons(myLis1,Cons(myLis2,Cons(myLis3, Empty)))


  val l = List(List(),List(1,2,3,4),List(11,22,33,44),List(90,20),List())


  println("myList.take --" + take(2)(myList))
  println("myList.head --" + myList.head)
  println("myList.tail --" + myList.tail)

  val myListAdd = (x:Int,y:Int) =>  y + x
  val myListAddStr = (x:Int,y:String) =>  y + x
  val doubleNum : Int => Int = x => x * 2


  println(s"Map demo ${map(doubleNum)(myList)}")
  println(s"fold res = ${fold(myList)(100)(myListAdd)}")
  println(s"fold resStr = ${fold(myList)("Hundred")(myListAddStr)}")


  println(s" Before flatten -- $l")
  println(s" After flatten -- ${flatten(l)}")

}
