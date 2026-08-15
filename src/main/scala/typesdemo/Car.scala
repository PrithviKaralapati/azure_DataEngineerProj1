package typesdemo

// Class = a blueprint you instantiate with `new` (or via a companion's apply below).
// It can hold mutable state (var) and constructor logic. Unlike a case class it gets no
// free equals/copy/toString — you write behavior explicitly.
class Car(val name: String, val topSpeed: Int) extends Movable with Honkable:
  private var odometer: Int = 0

  def drive(km: Int): Unit =
    odometer += km

  override def move(): String = s"$name drives at up to $topSpeed km/h"
  def status: String = s"$name has driven $odometer km so far"

// Companion object: same name as the class, same file, can see its private members.
// Common use: factory methods, so callers can write Car(...) instead of new Car(...).
object Car:
  def apply(name: String, topSpeed: Int): Car = new Car(name, topSpeed)

@main def runCarDemo(): Unit =
  val car = Car("Tesla Model 3", 250) // uses the companion's apply, not `new`
  car.drive(120)
  println(car.describe())
  println(car.status)
  println(car.honk()) // mixed in from the Honkable trait
