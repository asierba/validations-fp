package trypackage

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Success}

class PersonSpec extends AnyFunSpec with Matchers {
  describe("A try.Person") {
    it("should create valid person") {
      val expected = Success(Person("John Doe", 18, "johndoe@example.com"))
      val actual = Person.createWithTry("John Doe", 18, "johndoe@example.com")
      actual shouldEqual expected
    }

    it("should not create person when name is empty") {
      val tryPerson = Person.createWithTry("", 65, "david@thoughtworks.com")
      tryPerson match {
        case Success(_) => fail()
        case Failure(exception) =>
          exception match {
            case _: InvalidNameException => true shouldEqual true
            case _                       => fail()
          }
      }
    }

    it("should not create person when name does not start with uppercase") {
      val tryPerson =
        Person.createWithTry("asier", 65, "david@thoughtworks.com")
      tryPerson match {
        case Success(_) => fail()
        case Failure(exception) =>
          exception match {
            case _: InvalidNameException => true shouldEqual true
            case _                       => fail()
          }
      }
    }

    it("should not create person when age is above the maximum age") {
      val tryPerson = Person.createWithTry(
        "Asier",
        Person.MAXIMUM_AGE + 1,
        "david@thoughtworks.com"
      )
      tryPerson match {
        case Success(_) => fail()
        case Failure(exception) =>
          exception match {
            case _: InvalidAgeException => true shouldEqual true
            case _                      => fail()
          }
      }
    }

    it("should not create person when age is below the minimum age") {
      val tryPerson = Person.createWithTry(
        "Asier",
        Person.MINIMUM_AGE - 1,
        "david@thoughtworks.com"
      )
      tryPerson match {
        case Success(_) => fail()
        case Failure(exception) =>
          exception match {
            case _: InvalidAgeException => true shouldEqual true
            case _                      => fail()
          }
      }
    }

    it("should not create person when email is empty") {
      val tryPerson = Person.createWithTry("John Doe", 65, "")
      tryPerson match {
        case Success(_) => fail()
        case Failure(exception) =>
          exception match {
            case _: InvalidEmailException => true shouldEqual true
            case _                        => fail()
          }
      }
    }

    it("should not create person when email has not a @ character") {
      val tryPerson =
        Person.createWithTry("John Doe", 65, "john.doeATgoogle.com")
      tryPerson match {
        case Success(_) => fail()
        case Failure(exception) =>
          exception match {
            case _: InvalidEmailException => true shouldEqual true
            case _                        => fail()
          }
      }
    }
  }
}
