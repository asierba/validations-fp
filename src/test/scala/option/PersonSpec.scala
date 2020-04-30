package option

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers


class PersonSpec extends AnyFunSpec with Matchers {
  describe("Create a person") {
    it("should create valid person") {
      val expected = Person("John Doe", 18, "johndoe@example.com")
      val actual = Person.createOption("John Doe", 18, "johndoe@example.com")
      actual shouldEqual Some(expected)
    }

    it("should not create person when name is empty") {
      Person.createOption("", 65, "david@thoughtworks.com") shouldEqual None
    }

    it("should not create person when name does not start with uppercase") {
      Person.createOption("asier", 65, "david@thoughtworks.com") shouldEqual None
    }

    it("should not create person when age is above the maximum age") {
      Person.createOption("Asier", Person.MAXIMUM_AGE + 1, "david@thoughtworks.com") shouldEqual None
    }

    it("should not create person when age is below the minimum age") {
      Person.createOption("Asier", Person.MINIMUM_AGE - 1, "david@thoughtworks.com") shouldEqual None
    }

    it("should not create person when email is empty") {
      Person.createOption("John Doe", 65, "") shouldEqual None
    }

    it("should not create person when email has not a @ character") {
      Person.createOption("John Doe", 65, "john.doeATgoogle.com") shouldEqual None
    }
  }
}
