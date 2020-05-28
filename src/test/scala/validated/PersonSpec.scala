package validated

import cats.data.Validated.{Invalid, Valid}
import cats.data.{Chain, ValidatedNec}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import validated.InvalidError.InvalidError

class PersonSpec extends AnyFunSpec with Matchers {
  describe("A validated.Person") {

    it("should create valid person with cats") {
      val actual = Person.create("John Doe", 18, "johndoe@example.com")

      actual shouldEqual Valid(Person("John Doe", 18, "johndoe@example.com"))
    }

    it("should not create person with cats when name is empty") {
      val person: ValidatedNec[InvalidError, Person] =
        Person.create("", 65, "david@thoughtworks.com")
      person shouldEqual Invalid(Chain(InvalidError.InvalidNameError))
    }

    it("should not create person when name does not start with uppercase") {
      val person: ValidatedNec[InvalidError, Person] =
        Person.create("asier", 65, "david@thoughtworks.com")

      person shouldEqual Invalid(Chain(InvalidError.InvalidNameError))
    }

    it("should not create person when age is above the maximum age") {
      val person: ValidatedNec[InvalidError, Person] =
        Person.create("Asier", Person.MAXIMUM_AGE + 1, "david@thoughtworks.com")
      person shouldEqual Invalid(Chain(InvalidError.InvalidAgeError))
    }

    it("should not create person when age is below the minimum age") {
      val person: ValidatedNec[InvalidError, Person] =
        Person.create("Asier", Person.MINIMUM_AGE - 1, "david@thoughtworks.com")
      person shouldEqual Invalid(Chain(InvalidError.InvalidAgeError))
    }

    it("should not create person when email is empty") {
      val person: ValidatedNec[InvalidError, Person] =
        Person.create("John Doe", 65, "")

      person shouldEqual Invalid(Chain(InvalidError.InvalidEmailError))
    }

    it("should not create person when email has not a @ character") {
      val person: ValidatedNec[InvalidError, Person] =
        Person.create("John Doe", 65, "john.doeATgoogle.com")

      person shouldEqual Invalid(Chain(InvalidError.InvalidEmailError))
    }

//    it("should concatenate multiple errors") {
//      val person: ValidatedNec[InvalidError, Person] =
//        Person.createWithMultipleErrors("", Person.MAXIMUM_AGE + 1, "david@thoughtworks.com")
//      person shouldEqual Invalid(Chain(InvalidError.InvalidNameError, InvalidError.InvalidAgeError))
//    }
  }
}
