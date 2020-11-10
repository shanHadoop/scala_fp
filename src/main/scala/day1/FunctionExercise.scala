package day1


object FunctionExercise extends App {

  sealed trait mkToArray {
    //Single Abstract Method
    def mkArray(mk: String): Array[String]
    // concrete method
    def printString(str:String) = println(s"input string is $str")
  }

  // way 1
  val stringToArray = new mkToArray {
    override def mkArray ( myString : String ) : Array [ String ] = myString.split ( "" )
  }

  // way 2 SAM (Single Abstract Method)
  val stringToArray1 =  ( myString : String ) =>  myString.split ( "" )


//  Usually, we don’t need SAM in scala, as scala already has first class generic function type,
//  eta-expansion and a lot more. SAM reduces the readability as implicit conversion does. One can
//  always use type alias to give function a more understandable name, instead of using SAM.
//  SAM type cannot be pattern matched, at least for now.


  def f(i:Int):Int = i*2
  val ff:Int=>Int = (i) => i*2

  def g(i:Int):Int = i + 2
  val gg = (i:Int) => i + 2

  //eta expansion - The expression e _ is well-formed if e is of method type or
  // if e is a call-by-name parameter. If e is a method with parameters, e _ represents e converted
  // to a function type by eta expansion.
  // math.sin _	x => math.sin(x)

  //someMethod _ will roughly be translated to a new function object like this
//  new Function1[Int, Int] {
//    def apply(x: Int): Int = someMethod(x)
//  }
  List(f _ , g _)
  List[Int => Int](f, g)



  //***********************************************
  //A simple case for pipe
  //consider the following functions already defined
  type Amount = Double
  sealed trait Tax {
    val formulae:Amount => Amount
  }

  object Tax {
    case class VAT(formulae:Amount => Amount = _ * (3D / 100)) extends Tax
    case class ServiceTax(formulae:Amount => Amount = _ * (2D / 100)) extends Tax
    case class ServiceCharge(formulae:Amount => Amount) extends Tax
    val addTaxToTotal:Tax => Amount => Amount = tax => amount => tax.formulae(amount) + amount

    def addTaxOnBill(totalAmount:Amount):Amount = ???

    def addTaxOnBillWithDebug(totalAmount:Amount):Amount = ???

  }

}



