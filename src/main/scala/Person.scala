
case class Person(name: String, age: Int, email: String) {

}

object Person {
  val MINIMUM_AGE = 1
  val MAXIMUM_AGE = 120

  def create(name: String, age: Int, email: String): Person = {
    val validatedName = name match {
      case "" => throw InvalidNameException()
      case s if s.charAt(0) != s.toUpperCase().charAt(0) => throw InvalidNameException()
      case _ => name
    }
    val validatedAge = age match {
      case i if i < MINIMUM_AGE || i > MAXIMUM_AGE => throw InvalidAgeException()
      case _ => age
    }
    Person(validatedName, validatedAge, email)
  }
}

case class InvalidNameException() extends Exception

case class InvalidAgeException() extends Exception
