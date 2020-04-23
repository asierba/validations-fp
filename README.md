# validations-fp
Kata to learn how to do validations in functional programming with scala

# Requirements

Create a function that creates a user with the following parameters
* Name
* Age
* Email

This function should validate the following:
* The supplied name must not be empty and has to start with an uppercase character.
* The supplied age must be a number and must be between one and a hundred and twenty.
* The supplied email address must not be empty and must have at least a single @ character.

The idea is to implement error handling in different ways. Do it in 4 iterations

1. Using Exceptions
1. Using [Option](https://www.scala-lang.org/api/current/scala/Option.html)
1. Using [Either](https://www.scala-lang.org/api/2.9.3/scala/Either.html)
1. Using [Try](https://www.scala-lang.org/api/2.9.3/scala/util/Try.html)
1. Using [Validated from cats](https://typelevel.org/cats/datatypes/validated.html)

At the end of each iteration reflect. Do you like prefer way of doing error handling or the previous one? Why? Which are the tradeoffs of using different approaches?

Based on this: https://blog.ssanj.net/posts/2019-08-18-using-validated-for-error-accumulation-in-scala-with-cats.html
