package option

case class Person(name: String, age: Int, email: String) {}

object Person {
  def create(name: String, age: Int, email: String): Option[Person] = {
    try {
      validateName(name).map(name => Person(name, validateAge(age), validateEmail(email)))
    } catch {
      case _: Exception => None
    }
  }

  val MINIMUM_AGE = 1
  val MAXIMUM_AGE = 120

  private def validateEmail(email: String): String = {
    email match {
      case ""                    => throw InvalidEmailException()
      case e if !e.contains('@') => throw InvalidEmailException()
      case _                     => email
    }
  }

  private def validateAge(age: Int): Int = {
    age match {
      case i if i < MINIMUM_AGE || i > MAXIMUM_AGE =>
        throw InvalidAgeException()
      case _ => age
    }
  }

  private def validateName(name: String): Option[String] = {
    name match {
      case "" => None
      case s if s.charAt(0) != s.toUpperCase().charAt(0) =>
        None
      case _ => Some(name)
    }
  }
}

case class InvalidNameException() extends Exception

case class InvalidAgeException() extends Exception

case class InvalidEmailException() extends Exception