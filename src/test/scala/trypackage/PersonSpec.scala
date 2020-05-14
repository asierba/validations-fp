package trypackage

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Success


class PersonSpec extends AnyFunSpec with Matchers {
  describe("A try.Person") {
    it("should create valid person") {
      val expected = Success(Person("John Doe", 18, "johndoe@example.com"))
      val actual = Person.createWithTry("John Doe", 18, "johndoe@example.com")
      actual shouldEqual expected
    }

    it("should not create person when name is empty") {
      assertThrows[InvalidNameException] {
        Person.create("", 65, "david@thoughtworks.com")
      }
    }

    it("should not create person when name does not start with uppercase") {
      assertThrows[InvalidNameException] {
        Person.create("asier", 65, "david@thoughtworks.com")
      }
    }

    it("should not create person when age is above the maximum age") {
      assertThrows[InvalidAgeException] {
        Person.create("Asier", Person.MAXIMUM_AGE + 1, "david@thoughtworks.com")
      }
    }

    it("should not create person when age is below the minimum age") {
      assertThrows[InvalidAgeException] {
        Person.create("Asier", Person.MINIMUM_AGE - 1, "david@thoughtworks.com")
      }
    }

    it("should not create person when email is empty") {
      assertThrows[InvalidEmailException] {
        Person.create("John Doe", 65, "")
      }
    }

    it("should not create person when email has not a @ character") {
      assertThrows[InvalidEmailException] {
        Person.create("John Doe", 65, "john.doeATgoogle.com")
      }
    }
  }
}
