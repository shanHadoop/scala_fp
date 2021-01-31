object Hello extends App {
  class Foo(a: String)

  object Foo {
    def apply(a: String) = new Foo(a)
  }


  def someMethod(fooObj: Foo.type ) = fooObj.apply("x")


  def processAnyCode[T](opr: => T):T = {
  opr
  }

  println(processAnyCode("Shantanu Kumar Dutta"))
  println(processAnyCode(List(List(1, 2, 3, 4), List(5, 6, 7, 8)).flatMap(x=>x)))
  println(processAnyCode(100+100))

}
