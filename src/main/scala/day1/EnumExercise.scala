package day1

object EnumExercise extends App {
import EveryWeekDay._
def isWeekday(d:WeekDay) = !(d==SUNDAY || d==SATURDAY)
println(isWeekday(WEDNESDAY))
println(isWeekday(SATURDAY))
}


object EveryWeekDay extends Enumeration {
  type WeekDay = Value
   val SUNDAY, MONDAY , TUESDAY , WEDNESDAY , THURSDAY , FRIDAY, SATURDAY = Value
}
