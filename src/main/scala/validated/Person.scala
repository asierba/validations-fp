package validated

import cats.data.Validated
import cats.data.Validated.{Invalid, Valid}
import validated.InvalidError.InvalidError

object InvalidError extends Enumeration {
  type InvalidError = Value
  val InvalidNameError, InvalidAgeError, InvalidEmailError = Value
}

case class Person(name: String, age: Int, email: String) {}

object Person {

  val MINIMUM_AGE = 1
  val MAXIMUM_AGE = 120

  def create(name: String,
             age: Int,
             email: String): Validated[InvalidError, Person] = {

    val validatedName: Validated[validated.InvalidError.Value, String] =
      name match {
        case "" => Invalid(InvalidError.InvalidNameError)
        case s if s.charAt(0) != s.toUpperCase().charAt(0) =>
          Invalid(InvalidError.InvalidNameError)
        case _ => Valid(name)
      }
    val validatedAge = age match {
      case i if i < MINIMUM_AGE || i > MAXIMUM_AGE =>
        Invalid(InvalidError.InvalidAgeError)
      case _ => Valid(age)
    }
    val validatedEmail = email match {
      case ""                    => Invalid(InvalidError.InvalidEmailError)
      case e if !e.contains('@') => Invalid(InvalidError.InvalidEmailError)
      case _                     => Valid(email)
    }

    validatedName
      .andThen(_ => validatedAge)
      .andThen(age => validatedEmail.map(email => Person(name, age, email)))
  }
}

case class InvalidNameException() extends Exception

case class InvalidAgeException() extends Exception

case class InvalidEmailException() extends Exception
