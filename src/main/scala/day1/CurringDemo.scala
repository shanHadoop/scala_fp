package day1


sealed trait Color
object Color  {
case object Red extends Color
case object Green extends Color
case object Orange extends Color
case object Yellow extends Color
}


sealed trait Shape
object Shape{
  case object Triangle extends Shape
  case object Circle extends Shape
  case object Square extends Shape
}

case class ColoredShapes(s:Shape,c:Color){
  val draw = s"Shape $s with color $c ...."
}

object CurringDemo extends App {
def colorShape: Color =>Shape => ColoredShapes =  c => s => ColoredShapes(s,c)
def colorShape1(c:Color,s:Shape) : ColoredShapes = ColoredShapes(s,c)

def colorShapeGreen = colorShape(Color.Green)
 // this is not possible as this is not a curried function  colorShape1
 // def colorShapeRed = colorShape1(Color.Red)   // giving compilation error

  // here you forced compiler to convert a method to a function with help of  " _ "
def colorShapeRed = colorShape1(Color.Red,_)

println(colorShapeGreen(Shape.Circle).draw)
println(colorShapeGreen(Shape.Triangle).draw)


println(colorShapeRed(Shape.Triangle).draw)

}

