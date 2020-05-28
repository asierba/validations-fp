package validated

import cats.implicits._
import cats.data.Validated.Valid
import cats.data.{Validated, ValidatedNec}
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
             email: String): ValidatedNec[InvalidError, Person] = {

    val validatedName: ValidatedNec[validated.InvalidError.Value, String] =
      name match {
        case "" => Validated.invalidNec(InvalidError.InvalidNameError)
        case s if s.charAt(0) != s.toUpperCase().charAt(0) =>
          Validated.invalidNec(InvalidError.InvalidNameError)
        case _ => Valid(name)
      }
    val validatedAge = age match {
      case i if i < MINIMUM_AGE || i > MAXIMUM_AGE =>
        Validated.invalidNec(InvalidError.InvalidAgeError)
      case _ => Valid(age)
    }
    val validatedEmail = email match {
      case "" => Validated.invalidNec(InvalidError.InvalidEmailError)
      case e if !e.contains('@') =>
        Validated.invalidNec(InvalidError.InvalidEmailError)
      case _ => Valid(email)
    }

    (validatedName, validatedAge, validatedEmail).mapN(
      (name, age, email) => Person(name, age, email)
    )
  }
}

case class InvalidNameException() extends Exception

case class InvalidAgeException() extends Exception

case class InvalidEmailException() extends Exception
