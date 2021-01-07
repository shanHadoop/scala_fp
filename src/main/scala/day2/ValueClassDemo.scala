package day2

object ValueClassDemo extends App {

 //class Wrapper(val underlying: Int) extends AnyVal
 trait Printable extends Any {
    def print(): Unit = println(this)
  }

 class Wrapper(val underlying:Int) extends AnyVal with Printable{
   def + (a:Wrapper):Wrapper= new Wrapper(underlying + a.underlying)
 }

 // It has a single, public val parameter that is the underlying runtime representation.
 // The type at compile time is Wrapper, but at runtime, the representation is an Int.
 // A value class can define defs, but no vals, vars, or nested traits, classes or objects:

  val myAnyValue1 = new Wrapper(10)
  val myAnyValue2 = new Wrapper(20)
  val result = myAnyValue1 + myAnyValue2
  //  will not actually allocate any Wrapper instances, but will only use primitive doubles at runtime.
  //  Note: You can use case classes and/or extension methods for cleaner syntax in practice.

   println(myAnyValue1)
   println(myAnyValue2)
   println(result.underlying)




}
