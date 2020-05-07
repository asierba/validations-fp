package either

import either.InvalidError.InvalidError
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class PersonSpec extends AnyFunSpec with Matchers {
  describe("An Either.Person") {
    it("should create valid person") {
      val actual = Person.createEither("John Doe", 18, "johndoe@example.com")

      actual shouldEqual Right(Person("John Doe", 18, "johndoe@example.com"))
    }

    it("should not create person when name is empty") {
      val person: Either[InvalidError, Person] =
        Person.createEither("", 65, "david@thoughtworks.com")
      person shouldEqual Left(InvalidError.InvalidNameError)
    }

    it("should not create person when name does not start with uppercase") {
      val person: Either[InvalidError, Person] =
        Person.createEither("asier", 65, "david@thoughtworks.com")

      person shouldEqual Left(InvalidError.InvalidNameError)
    }

    it("should not create person when age is above the maximum age") {
      val person: Either[InvalidError, Person] = Person.createEither(
        "Asier",
        Person.MAXIMUM_AGE + 1,
        "david@thoughtworks.com"
      )
      person shouldEqual Left(InvalidError.InvalidAgeError)
    }

    it("should not create person when age is below the minimum age") {
      val person: Either[InvalidError, Person] = Person.createEither(
        "Asier",
        Person.MINIMUM_AGE - 1,
        "david@thoughtworks.com"
      )
      person shouldEqual Left(InvalidError.InvalidAgeError)
    }

    it("should not create person when email is empty") {
      val person: Either[InvalidError, Person] =
        Person.createEither("John Doe", 65, "")

      person shouldEqual Left(InvalidError.InvalidEmailError)
    }

    it("should not create person when email has not a @ character") {
      val person: Either[InvalidError, Person] =
        Person.createEither("John Doe", 65, "john.doeATgoogle.com")

      person shouldEqual Left(InvalidError.InvalidEmailError)
    }
  }
}
