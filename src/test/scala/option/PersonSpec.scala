package option

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers


class PersonSpec extends AnyFunSpec with Matchers {
  describe("Create a person") {
    it("should create valid person") {
      val expected = Person("John Doe", 18, "johndoe@example.com")
      val actual = Person.create("John Doe", 18, "johndoe@example.com")
      actual shouldEqual Some(expected)
    }

    it("should not create person when name is empty") {
      Person.create("", 65, "david@thoughtworks.com") shouldEqual None
    }

    it("should not create person when name does not start with uppercase") {
      Person.create("asier", 65, "david@thoughtworks.com") shouldEqual None
    }

    it("should not create person when age is above the maximum age") {
      Person.create("Asier", Person.MAXIMUM_AGE + 1, "david@thoughtworks.com") shouldEqual None
    }

    it("should not create person when age is below the minimum age") {
      Person.create("Asier", Person.MINIMUM_AGE - 1, "david@thoughtworks.com") shouldEqual None
    }

    it("should not create person when email is empty") {
      Person.create("John Doe", 65, "") shouldEqual None
    }

    it("should not create person when email has not a @ character") {
      Person.create("John Doe", 65, "john.doeATgoogle.com") shouldEqual None
    }
  }
}
