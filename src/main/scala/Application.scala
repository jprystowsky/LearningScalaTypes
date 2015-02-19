import models._

object Application extends App {
	// Type invariance
	println(new DogGroomer().groom(new Terrier("Roxy")))
	println(new DogGroomer().groom(new Dog("Ralph")))

	// Covariance
	println(new SnakeCatcher().catchPet(new Pet[Snake](new Snake("Hans"))))
	println(new SnakeCatcher().catchPet(new Pet[Boa](new Boa("Franz"))))

	// Contravariance
	println(new SteakBiteEater().eatMeat(new Meat[SteakBite](new SteakBite())))
	println(new SteakBiteEater().eatMeat(new Meat[Steak](new Steak())))
}
