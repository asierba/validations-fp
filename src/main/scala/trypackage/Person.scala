package trypackage

import either.Person

import scala.util.{Failure, Success, Try}

case class Person(name: String, age: Int, email: String) {}

object Person {

  val MINIMUM_AGE = 1
  val MAXIMUM_AGE = 120

  def create(name: String, age: Int, email: String): Try[Person] = {
      val validatedName = name match {
        case "" => Failure(InvalidNameException())
        case s if s.charAt(0) != s.toUpperCase().charAt(0) =>
          Failure(InvalidNameException())
        case _ => Success(name)
      }
      val validatedAge = age match {
        case i if i < MINIMUM_AGE || i > MAXIMUM_AGE =>
          Failure(InvalidAgeException())
        case _ => Success(age)
      }
      val validatedEmail = email match {
        case ""                    => Failure(InvalidEmailException())
        case e if !e.contains('@') => Failure(InvalidEmailException())
        case _                     => Success(email)
      }

      for {
        name <- validatedName
        age <- validatedAge
        email <- validatedEmail
      } yield Person(name, age, email)
    }
}

case class InvalidNameException() extends Exception

case class InvalidAgeException() extends Exception

case class InvalidEmailException() extends Exception
