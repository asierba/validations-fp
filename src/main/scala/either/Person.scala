package either

import either.InvalidError.InvalidError

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
             email: String): Either[InvalidError, Person] = {

      val validatedName = name match {
        case "" => Left(InvalidError.InvalidNameError)
        case s if s.charAt(0) != s.toUpperCase().charAt(0) =>
          Left(InvalidError.InvalidNameError)
        case _ => Right(name)
      }
      val validatedAge = age match {
        case i if i < MINIMUM_AGE || i > MAXIMUM_AGE =>
          Left(InvalidError.InvalidAgeError)
        case _ => Right(age)
      }
      val validatedEmail = email match {
        case ""                    => Left(InvalidError.InvalidEmailError)
        case e if !e.contains('@') => Left(InvalidError.InvalidEmailError)
        case _                     => Right(email)
      }
      validatedName.flatMap(name => validatedAge.flatMap(age => validatedEmail.flatMap(email => Right(Person(name,age,email)))))
  }
}

case class InvalidNameException() extends Exception

case class InvalidAgeException() extends Exception

case class InvalidEmailException() extends Exception
