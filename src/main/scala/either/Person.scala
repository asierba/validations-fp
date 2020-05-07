package either

import either.InvalidError.InvalidError

object InvalidError extends Enumeration {
  type InvalidError = Value
  val InvalidNameError: either.InvalidError.Value = Value
}

case class Person(name: String, age: Int, email: String) {

}

object Person {

  val MINIMUM_AGE = 1
  val MAXIMUM_AGE = 120

  def createEither(name: String, age: Int, email: String): Either[InvalidError, Person] = {
    try {
      Right(create(name, age, email))
    }catch {
      case _:InvalidNameException => Left(InvalidError.InvalidNameError)
    }
  }
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
    val validatedEmail = email match {
      case "" => throw InvalidEmailException()
      case e if !e.contains('@') => throw InvalidEmailException()
      case _ => email
    }
    Person(validatedName, validatedAge, validatedEmail)
  }
}

case class InvalidNameException() extends Exception

case class InvalidAgeException() extends Exception

case class InvalidEmailException() extends Exception
