package day1

object CreatingTypeExercise extends App {

  case class Employee(
    eid:String,
    name:String,
    add:String,
    city:String,
    pinCode:Int,
    state:String
  )

  // updating existing case object, by default all variable are immutable/val of the case class if you are not mentioned
  // var explicitly.
  val emp= Employee("51557149","SKD","BANG","BANG",0,"KA").copy(pinCode = 560068)
//  emp.pinCode = 560068
  println(emp)


  //Structure type
  class SomeClassIntPrint {
    def print1:(Int)=> String  = (x) => s"Int -> String $x"
    def print2:(Boolean)=> String  = (x)=> s"Int -> String $x"
  }

  class SomeClassBooleanPrint {
    def print1:(Boolean)=> String  = (x)=> s"Int -> String $x"
  }

  // here function/method name should be same as the method of the class , which object we want to pass.
  def myFuc[A,B](stc:{def print1:Int => String}) = println(stc.print1(1000))
  def myFuc1[A,B](stc:{def print2:Boolean => String}) = println(stc.print2(true))


  myFuc(new SomeClassIntPrint)
  myFuc1(new SomeClassIntPrint)


}
