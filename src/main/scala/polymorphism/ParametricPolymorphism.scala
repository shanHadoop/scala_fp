package polymorphism



object ParametricPolymorphism extends App{
//. We specify a function with a generic parameter, then, when we instantiate that
// function or whatever, we specify our current type, which then gets applied to the function.
  trait GenericStack[T]{
    var myGenList:List[T]= Nil
    def push(e:T) {myGenList = e :: myGenList}
    def peak():T = myGenList.head
    def pop():T = {
      val h = myGenList.head
      myGenList=myGenList.tail
      h
    }
  }

  val inStack = new GenericStack[Int] {}
  inStack.push(10)
  inStack.push(11)
  inStack.push(12)
  println(inStack.pop())

}
