package typesdemo2

// Object = a singleton. Scala has no `static` keyword — instead you get exactly one
// instance, created lazily on first use and shared everywhere it's imported.
// Unlike Car's companion object, this one stands alone (no matching class of the same name).
object VehicleFactory:
  private var vehiclesBuilt = 0

  def buildCar(name: String, topSpeed: Int): Car =
    vehiclesBuilt += 1
    Car(name, topSpeed)

  def buildBike(name: String, topSpeed: Int): Bike =
    vehiclesBuilt += 1
    Bike(name, topSpeed)

  def totalBuilt: Int = vehiclesBuilt
