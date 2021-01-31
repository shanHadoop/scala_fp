package polymorphism

object AdocPolymorphism extends App {

  //. With ad-hoc polymorphism, we can actually change the behavior of the
  // generic code based on the type we polymorph on! Let's see an example:

  // Polymorphic function that act diffrent depending on the type of given argument

  trait adhocGenericStack[T] {

    var myGenList: List[T] = Nil

    def push(e: T) {
      myGenList = e :: myGenList
    }

    def peak(): T = myGenList.head

    def pop()(implicit crazy:Crazy[T]): T = {
      crazy.omg
      val h = myGenList.head
      myGenList = myGenList.tail
      h
    }
  }

  trait Crazy[T]{
  def omg()
  }


  implicit val crazyInt:Crazy[Int] = new Crazy[Int]{
    def omg = println(" It is crazy Int")
  }

  val crazyDouble:Crazy[Double] = new Crazy[Double]{
    def omg = ()
  }
  val crazyDouble1:Crazy[Double] = new Crazy[Double]{
    def omg = println("Crazy Doble omg behaviour has been changes as per the object")
  }


  val intCrazyStack = new adhocGenericStack[Int]{}
  intCrazyStack.push(1)
  intCrazyStack.push(2)
  println(intCrazyStack.pop())

  val doubleCrazyStack = new adhocGenericStack[Double] {}
  doubleCrazyStack.push(10.1)
  doubleCrazyStack.push(11.1)
  println(doubleCrazyStack.pop()(crazyDouble))
  // not found any implicit so explicitly passed
  val doubleCrazyStack1 = new adhocGenericStack[Double] {}
  doubleCrazyStack1.push(10.1)
  doubleCrazyStack1.push(11.1)
  println(doubleCrazyStack1.pop()(crazyDouble1))    // not found any implicit so explicitly passed




  //  val lInt = List(1, 4, 57, 2, 4, 99)
  //  println(lInt.sorted)
  //
  //  case class Student(id: Int)
  //
  //  val stuList = List(Student(1), Student(4), Student(57), Student(2), Student(4), Student(99))
  //  //  println( stuList.sorted) // No implicit arguments of type: Ordering[StudentId]
  //  val studOrd: Ordering[Student] = ((x, y) => x.id compareTo y.id)
  //  println(stuList.sorted(studOrd))
  //
  //
  //  //
  //  case class Complex(re: Double, le: Double) {
  //    def +(op: Complex) = Complex(op.re + re, op.le + le)
  //    def -(op: Complex) = Complex(op.re - re, op.le - le)
  //  }
  //
  //
  //  trait Addable[A] {
  //    def add(other: A): A
  //  }
  //
  //
  //  // <%  - View bounds (deprecated) type class or View bound
  //  // <: >:      // Upper and lower bounds
  ////
  ////  def compose[A <: Addable[A]](x: A, y: A) = x add y
  ////
  ////  println(compose[Int](10, 20))
}
