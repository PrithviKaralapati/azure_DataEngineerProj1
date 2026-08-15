package typesdemo

// Case Class = an immutable data holder. The compiler auto-generates equals, hashCode,
// toString, and a copy() method, and lets you skip `new` when constructing (Bike(...)).
case class Bike(name: String, topSpeed: Int) extends Movable:
  override def move(): String = s"$name pedals at up to $topSpeed km/h"

@main def runBikeDemo(): Unit =
  val bike = Bike("Trek FX", 35)          // no `new` needed
  val faster = bike.copy(topSpeed = 40)   // copy() comes free
  println(bike.describe())
  println(s"copy   -> $faster")
  println(s"equals -> ${bike == Bike("Trek FX", 35)}") // structural equality, free
