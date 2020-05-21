package validated

import cats.data.Validated
import cats.data.Validated.{Invalid, Valid}
import validated.InvalidError.InvalidError
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class PersonSpec extends AnyFunSpec with Matchers {
  describe("An Either.Person") {

    it("should create valid person with cats") {
      val actual = Person.create("John Doe", 18, "johndoe@example.com")

      actual shouldEqual Valid(Person("John Doe", 18, "johndoe@example.com"))
    }

    it("should not create person with cats when name is empty") {
      val person: Validated[InvalidError, Person] =
        Person.create("", 65, "david@thoughtworks.com")
      person shouldEqual Invalid(InvalidError.InvalidNameError)
    }

    it("should not create person when name does not start with uppercase") {
      val person: Validated[InvalidError, Person] =
        Person.create("asier", 65, "david@thoughtworks.com")

      person shouldEqual Invalid(InvalidError.InvalidNameError)
    }

    it("should not create person when age is above the maximum age") {
      val person: Validated[InvalidError, Person] =
        Person.create("Asier", Person.MAXIMUM_AGE + 1, "david@thoughtworks.com")
      person shouldEqual Invalid(InvalidError.InvalidAgeError)
    }

    it("should not create person when age is below the minimum age") {
      val person: Validated[InvalidError, Person] =
        Person.create("Asier", Person.MINIMUM_AGE - 1, "david@thoughtworks.com")
      person shouldEqual Invalid(InvalidError.InvalidAgeError)
    }

    it("should not create person when email is empty") {
      val person: Validated[InvalidError, Person] =
        Person.create("John Doe", 65, "")

      person shouldEqual Invalid(InvalidError.InvalidEmailError)
    }

    it("should not create person when email has not a @ character") {
      val person: Validated[InvalidError, Person] =
        Person.create("John Doe", 65, "john.doeATgoogle.com")

      person shouldEqual Invalid(InvalidError.InvalidEmailError)
    }
  }
}
