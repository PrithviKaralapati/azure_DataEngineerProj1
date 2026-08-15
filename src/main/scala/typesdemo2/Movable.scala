package typesdemo2

// Trait = a contract (like an interface) that can also carry real logic.
// You never `new` a trait directly — you mix it into a class/case class with extends/with.
trait Movable:
  def move(): String // abstract — every mixer must implement this
  def describe(): String = s"This vehicle can move: ${move()}" // concrete — shared for free

trait Honkable:
  def honk(): String = "Beep beep!" // fully concrete; mixers can override it if they want
