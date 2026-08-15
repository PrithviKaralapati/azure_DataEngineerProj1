package typesdemo2

@main def runTypesDemo2(): Unit =
  println(s"Vehicle demo v$appVersion\n")

  // Class
  val car = Car("Tesla Model 3", 250)
  car.drive(120)
  println(s"[Class]      ${car.describe()}")
  println(s"[Class]      ${car.status}")

  // Trait (Movable/Honkable, mixed into Car and Bike)
  println(s"[Trait]      ${car.honk()}")

  // Case Class
  val bike = Bike("Trek FX", 35)
  val fasterBike = bike.copy(topSpeed = 40) // copy() comes free with case classes
  println(s"[Case Class] ${bike.describe()}")
  println(s"[Case Class] copy -> $fasterBike")
  println(s"[Case Class] equals -> ${bike == Bike("Trek FX", 35)}") // structural equality, free

  // Enum
  List(VehicleType.CarType, VehicleType.BikeType, VehicleType.TruckType)
    .foreach(t => println(s"[Enum]       ${t.description}"))

  // Object (singleton)
  val pickup = VehicleFactory.buildCar("Ford F-150", 180)
  VehicleFactory.buildBike("Giant Escape", 30)
  println(s"[Object]     VehicleFactory has built ${VehicleFactory.totalBuilt} vehicles")

  // File-level (plain top-level defs/vals from Utils.scala)
  println(formatReport(Seq(car.status, bike.toString, pickup.status)))
