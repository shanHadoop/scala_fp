package day1

object ADTExercise extends App {
  //What is a Type
  //What is a Value

  //A type with empty set
  lazy val x: Nothing = ???
  //A type with singleton set
  val u: Unit = ()
  //Tuples in scala
  val t = (1, "rr")
  //basic scala constructs use to represent a type {class, case class, trait, object, case object, sealed trait, Enumeration} and type alias

  //What is ADT? (Algebraic data type)
  //https://about.chatroulette.com/posts/algebraic-data-types/

  //Sum type e.g. Boolean, Option, Either, etc. Or
  def div(n: Int, d: Int): Option[Int] = if (d == 0) None else Some(n / d)

  def div1(n: Int, d: Int): Either[String, Int] =
    if (d == 0) Left("Div by zero not possible") else Right(n / d)

  /**
    * domain modeling using ADT
    * IoT Domain, representing devices communicating with Http and others with MQtt
    */
  type HostPort = (String, String)
  sealed trait IoTProtocol
  case class HTTP(host: String, port: String, isSecure: Boolean)
      extends IoTProtocol
  case class MQTT(brokers: List[HostPort], isSecure: Boolean)
      extends IoTProtocol

  /**
    * One more example, Shipping types
    */
  sealed trait ShippingType
  case object Post extends ShippingType
  case object FedX extends ShippingType
  case object DHL extends ShippingType

  /*-All exercises below are assumed to be solved only using sealed trait , case classes and case objects-*/
  /**
    * Exercise 1
    * Data model of a credit card
    */
  println("Exercise 1..................")
  sealed trait CreditCard
  case class VisaCreditCard(name:String,startDate:String,expiryDate:String, cardType:String="VISA") extends CreditCard
  case class RubayCreditCard(name:String,expiryDate:String,cardType:String="Rubay") extends CreditCard
  case class MasterCredit(name:String,expiryDate:String,cardType:String="Master") extends CreditCard

  /**
    * Exercise 2
    * Data model of a Paint Application
    */
  println("Exercise 2..................")
  sealed trait Paint

  /**
    * lets do some pattern matching
    * https://docs.scala-lang.org/tour/pattern-matching.html
    * https://docs.scala-lang.org/overviews/scala-book/match-expressions.html
    * For more details check here https://scala-lang.org/files/archive/spec/2.12/08-pattern-matching.html
    */
  println("Exercise 3..................")
  //1.  Here we actually see the usage of unapply with match keyword
  //2.  Wild card or otherwise conditions
  //3.  guards in pattern matching (if)
  //4.  Handling alternate cases(|)
  //5.  back ticks and capital letters in pattern match https://users.scala-lang.org/t/upper-case-letters-in-pattern-matching/5947/2
  //6.  Alias in pattern matching
  //7.  sealed traits and match errors in pattern matching

  val http: IoTProtocol = HTTP("", "", isSecure = false)

  def processRequest(protocol: IoTProtocol): String =
    protocol match {
      case MQTT(bs, b)  => s"Running a MQTT broker for servers ${bs.mkString(",")} and isSecure $b"
      case HTTP(h, p, b) if b => s"Running a secure HTTP server for $h, $p"
      case http@HTTP(_, _, _) => s"Running a HTTP server for $http"
      //case _ => s"Not an MQTT"
    }

  val str = "2"
  val S = "a"
  val D = "b"
  str match {
    case S | D | "c" | `str` => println("This is correct")
  }

  /**
    * Exercise 4
    * Pattern match on an expression to print what type of value it returns
    */
  println("Exercise 4..................")
  def printTypes(printMyType: Any): Unit =
    printMyType match {
      case x:Int => println("Int")
      case x:Boolean => println("Boolean")
      case x:Double => println("Double")
      case x:Long => println("Long")
      case _ => println("Unknown Type.")
    }
println(s"${printTypes(1000L)}")
  /**
    * Exercise 5
    * Pattern matching with regular expression
    * Note : uncomment and implement
    */
  println("Exercise 5..................")
  val date = raw"""(\d{4})-(\d{2})-(\d{2})""".r

  val dateValidation = "2004-01-20" match {
    case date => "It's a date!"
    case _ => "It's not a valid date"
  }
  println(s"$dateValidation")


  val myYear= "2004-01-20" match {
    case s"$year-$month-$date" => println(s"year= $year, month= $month , date= $date")
  }

  /**
    * Exercise 6
    * Pattern match on an Option inside either
    */
  println("Exercise 6..................")
  type Input = Either[String, Option[String]]
  def getStringFrom(input:Input) : String = input match {
    case Right(myData) => s"implement this method $myData"
    case Left(myData) => s"implement this method error $myData"
  }
  val myInputRight = Right(Some("I am right"))
  println(getStringFrom(myInputRight))
  val myInputEither = Left("There are some error")
  println(getStringFrom(myInputEither))
  /**
    * Exercise 7
    * Use Pattern match on tuple for below result
    * {{{
    *   if _1 == _2 then Option(_1 + _2)
    *   if _1 < _2 then Option(_1)
    *   else None
    * }}}
    */
  println("Exercise 7..................")
  def computeTuple(t: (Int, Int)): Option[Int] = {
    t match {
      case (v1,v2) if v1 == v2 => Some(v1+v2)
      case (v1,v2) if v1 < v2 => Some(v1)
      case _  => None
      // OR
      //      case x if x._1 == x._2 => Some(x._1 + x._2)
      //      case x if x._1 <= x._2 => Some(x._1)
      //      case _  => None

    }
  }
println(computeTuple(100,200))
  println(computeTuple(100,100))
  /**
    * Exercise 8
    *
    * Pattern match on multiple object containing a domain
    */
  case class Address(
      street: String,
      city: String,
      postalCode: String,
      state: String
  )
  sealed trait PhoneType
  case object HomePhone extends PhoneType
  case object WorkPhone extends PhoneType
  case object Other extends PhoneType

  case class Person(
      firstName: String,
      lastName: String,
      address: Address,
      phoneType: PhoneType
  )
  def checkIfPersonHasWorkPhone(person: Person): Boolean = {
    person.phoneType match {
      case WorkPhone =>  true
      case _ => false
    }

  }
  def checkIfPersonAreFromGivenCity(person: Person, city: String): Boolean = {
    person.address.city match {
      case city => true
      case _  => false
    }
  }
  sealed trait Errors
  case object InvalidAddress extends Errors
  case object InvalidPerson extends Errors
  //helper functions
  def emptyString: String => Boolean = x => x.isEmpty

  //Lets make some smart constructors
  def makeAddress(address: Address): Either[Errors, Address] = {
  if (emptyString(address.city)) Left(InvalidAddress) else Right(address)

  }
  def makePerson(person: Person): Either[Errors, Person] = {
    if (emptyString(person.firstName)) Left(InvalidPerson) else Right(person)
  }

  println(makeAddress(Address("street","city","postalcode","state")))
  println(makeAddress(Address("street","","postalcode","state")))
  //Testing, create a sample person and tryout the person smart constructor

  println(makePerson(Person("","lastname",Address("street","","postalcode","state"),HomePhone)))
  println(makePerson(Person("firstName","lastName",Address("street","","postalcode","state"),HomePhone)))
}
