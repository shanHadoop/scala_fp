package polymorphism

object TypeClassDemoWithExplanation extends App{
  trait Animal
  case class Dog(name:String) extends Animal
  case class Cat(name:String) extends Animal
  case class Bird(name:String) extends Animal

  //step1:--- define a Type class BehaveLikeHuman with generic type A, let us apply new functionality
  //          to whatever type /A we want . for instant if we want to apply speak for a Dog not for Cat

  trait BehaveLikeHuman[A]{
    def speak(a:A)
  }


  //Step2:-- Create instance of Type class/BehaveLikeHuman for a type A / Dog , you want enhance
  //         feature like speak
  //  Instances of the type class for the data types you want to extend

  object BehaveLikeHumanInstance {
    implicit val dogBehaveLikeHuman = new BehaveLikeHuman[Dog] {
      override def speak(a: Dog): Unit = println(s"I am a Dog name ${a.name} can Speak")
    }
  }


 // Step:- 3  Created an Interface to call speak of Dog Instance
  object BehaveLikeHuman1 {
    def speakasHuman[A](a:A)(implicit behaveLikeHuman: BehaveLikeHuman[A]): Unit ={
      behaveLikeHuman.speak(a)
    }
  }

  import BehaveLikeHumanInstance.dogBehaveLikeHuman
  val rover = Dog("Rover")
  BehaveLikeHuman1.speakasHuman(rover)

}

