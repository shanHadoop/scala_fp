package polymorphism

class AdhocPolymorphism1 {
trait Car[T]{
  def vin(car:T):String;
  def model(car:T):String
  def price(car:T):Double
  def setPrice(car:T,newPrice:Double):T
}

implicit object objSedan extends Car[Sedan]{
  def vin(car:Sedan):String = car.vin;
  def model(car:Sedan):String = car.model
  def price(car:Sedan):Double = car.price
  def setPrice(car:Sedan,newPrice:Double) = car.copy(price=newPrice)
}


  implicit object objSport extends Car[Sports]{
    def vin(car:Sports):String = car.vin;
    def model(car:Sports):String = car.model
    def price(car:Sports):Double = car.price
    def setPrice(car:Sports,newPrice:Double) = car.copy(price=newPrice)
  }

  case class Sedan(vin:String,model:String,price:Double)
  case class Sports(vin:String,model:String,price:Double)

  // Ad-hoc polymorphism example continued
   implicit class CarOps[T](car: T)(implicit ev: Car[T]) {
    def setPrice(newPrice: Double): T = ev.setPrice(car: T, newPrice: Double)
  }  // it is used for class conversion as setprice comes under Car[T] type not under Sedan and Sprot


  // Ad-hoc polymorphism example continued
  val sedan1 = Sedan("1ABC*234", "Honda Accord", 20500)
  sedan1.setPrice(19500)
  // res1: Sedan = Sedan("1ABC*234", "Honda Accord", 19500.0)


}
