package manning.fp

package skd.com.scalaLearning

object CustomisedOptionTypeDemo extends App {
  val myOption = SomeSKD(10)
  println(myOption.map(_*20))
  val afterFlat= myOption.flatMap(x => SomeSKD(x+" 20"))

  println(afterFlat)
  println(myOption.filter(x => x >10))
}


abstract class OptionSkd[+A] {
  def map[B](f: A => B): OptionSkd[B]

  def flatMap[B](f: A => OptionSkd[B]): OptionSkd[B]

  def filter[B](f: A => Boolean): OptionSkd[A]
}

case object NoneSKD extends OptionSkd[Nothing] {
  override def map[B](f: Nothing => B): OptionSkd[B] = NoneSKD

  override def flatMap[B](f: Nothing => OptionSkd[B]): OptionSkd[B] = NoneSKD

  override def filter[B](f: Nothing => Boolean): OptionSkd[Nothing] = NoneSKD
}

case class SomeSKD[+A](values: A) extends OptionSkd[A] {
  def map[B](f: A => B): SomeSKD[B] = SomeSKD(f(values))

  def flatMap[B](f: A => OptionSkd[B]): OptionSkd[B] = f(values)

  def filter[B](f: A => Boolean): OptionSkd[A] = {
    if (f(values)) this
    else NoneSKD
  }
}