package models

/**
 * Simple example of invariance.
 *
 * Dogs are Animals, and Terriers are Dogs. Hence, Groomer[Dog] can automatically
 * groom() a Terrier as well.
 */

class Dog(val name: String)
class Terrier(override val name: String) extends Dog(name)

trait Groomer[T] {
	def groom(t: T): String
}

class DogGroomer extends Groomer[Dog] {
	override def groom(t: Dog): String = s"${t.name} was groomed"
}

/**
 * Simple example of covariance.
 *
 * SnakeCatcher should be able to catch a Pet[Boa] if it can catch a Pet[Snake],
 * hence Pet must be declared covariant in T -- Pet[+T].
 */

class Pet[+T](val pet: T)
class Snake(val name: String)
class Boa(override val name: String) extends Snake(name)

trait PetCatcher[T] {
	def catchPet(t: T): String
}

class SnakeCatcher extends PetCatcher[Pet[Snake]] {
	override def catchPet(pet: Pet[Snake]): String = s"${pet.pet.name} has been caught"
}

/**
 * Simple example of contravariance.
 *
 * A SteakBiteEater should be able to eat Meat[Steak] if it can eat Meat[SteakBite],
 * so Meat is declared contravariant in T -- Meat[-T].
 *
 * Note that Meat cannot take meat as a var or val. Hence, we define a trait NamedMeat
 * that Steak and SteakBite implement and create a type bound on contravariant T
 * to access this method.
 */

class Meat[-T <: NamedMeat](meat: T) {
	val name = meat.name
}
trait NamedMeat {
	def name: String
}
class Steak extends NamedMeat {
	override def name: String = "An entire steak"
}
class SteakBite extends Steak {
	override def name: String = "A bite of steak"
}

trait MeatEater[T] {
	def eatMeat(t: T): String
}

class SteakBiteEater extends MeatEater[Meat[SteakBite]] {
	override def eatMeat(m: Meat[SteakBite]): String = s"${m.name} has been eaten"
}