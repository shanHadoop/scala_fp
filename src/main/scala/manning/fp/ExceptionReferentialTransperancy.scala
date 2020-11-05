package manning.fp

object ExceptionReferentialTransperancy extends App {

//throwing exceptions is a side effect, it is not referential transparent
  def throwException(i:Int) ={
    val y:Int = throw new Exception("Failed")
    try{
      val x = i + 40
      x + y
    }catch {
      case e:Exception => 43
    }
  }
//  println(throwException(10))  // Exception in thread "main" java.lang.Exception: Failed


  def throwExceptionWithRF(i:Int)={
    try{
      val x = i + 40
      x + ((throw new Exception("Failed")):Int )   // replaced with value
    }catch {
      case e:Exception => 43
    }

  }
  println(throwExceptionWithRF(10))    // 43 , out put is not same if we replace the expression with value
  //  There are two main problems with exceptions:
  //   As we just discussed, exceptions break RT and introduce context dependence, moving us
  //    away from the simple reasoning of the substitution model and making it possible
  //  to write confusing exception-based code. This is the source of the folklore advice
  //    that exceptions should be used only for error handling, not for control flow.
  //  Listing 4.1 Throwing and catching an exception
  //  val y: Int = ... declares y
  //    as having type Int and sets it
  //    equal to the right-hand side of =.
  //  A catch block is just a patternmatching block like the ones we’ve seen.
  //  case e: Exception is a pattern
  //    that matches any Exception, and it
  //    binds this value to the identifier e. The
  //  match returns the value 43.
  //  A thrown Exception can be
  //  given any type; here we’re
  //  annotating it with the type Int.
  //    Licensed to Emre Sevinc <emre.sevinc@gmail.com>
  //  50 CHAPTER 4 Handling errors without exceptions
  //   Exceptions are not type-safe. The type of failingFn, Int => Int tells us nothing
  //    about the fact that exceptions may occur, and the compiler will certainly not
  //    force callers of failingFn to make a decision about how to handle those exceptions. If we forget to check for an exception in failingFn, this won’t be detected
  //  until runtime.


}
