package typesdemo

// Trait = a contract (like an interface) that can also carry real logic.
// You never `new` a trait directly — you mix it into a class/case class with extends/with.
trait Movable:
  def move(): String // abstract — every mixer must implement this
  def describe(): String = s"This vehicle can move: ${move()}" // concrete — shared for free

trait Honkable:
  def honk(): String = "Beep beep!" // fully concrete; mixers can override it if they want

// Traits have no entry point of their own, so to run one standalone we mix it into an
// anonymous instance right here just to exercise it.
@main def runMovableDemo(): Unit =
  val demoVehicle = new Movable with Honkable:
    def move(): String = "a demo vehicle glides silently"
  println(demoVehicle.describe())
  println(demoVehicle.honk())
