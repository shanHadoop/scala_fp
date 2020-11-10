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


}
