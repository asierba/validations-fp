package trypackage

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.TryValues._

import scala.util.Success

class PersonSpec extends AnyFunSpec with Matchers {
  describe("A try.Person") {
    it("should create valid person") {
      val expected = Success(Person("John Doe", 18, "johndoe@example.com"))
      val actual = Person.create("John Doe", 18, "johndoe@example.com")
      actual shouldEqual expected
    }

    it("should not create person when name is empty") {
      Person
        .create("", 65, "david@thoughtworks.com")
        .failure
        .exception shouldBe a[InvalidNameException]
    }

    it("should not create person when name does not start with uppercase") {
      Person
        .create("asier", 65, "david@thoughtworks.com")
        .failure
        .exception shouldBe a[InvalidNameException]
    }

    it("should not create person when age is above the maximum age") {
      Person
        .create("Asier", Person.MAXIMUM_AGE + 1, "david@thoughtworks.com")
        .failure
        .exception shouldBe a[InvalidAgeException]
    }

    it("should not create person when age is below the minimum age") {
      Person
        .create("Asier", Person.MINIMUM_AGE - 1, "david@thoughtworks.com")
        .failure
        .exception shouldBe a[InvalidAgeException]
    }

    it("should not create person when email is empty") {
      Person
        .create("John Doe", 65, "")
        .failure
        .exception shouldBe a[InvalidEmailException]
    }

    it("should not create person when email has not a @ character") {
      Person
        .create("John Doe", 65, "john.doeATgoogle.com")
        .failure
        .exception shouldBe a[InvalidEmailException]
    }
  }
}
