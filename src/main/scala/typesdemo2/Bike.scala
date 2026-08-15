package typesdemo2

// Case Class = an immutable data holder. The compiler auto-generates equals, hashCode,
// toString, and a copy() method, and lets you skip `new` when constructing (Bike(...)).
case class Bike(name: String, topSpeed: Int) extends Movable:
  override def move(): String = s"$name pedals at up to $topSpeed km/h"
