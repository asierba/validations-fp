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

The idea is to implement error handling it in different ways. Split this in 4 iterations

Iteration 1: Use exceptions
Iteration 2: Options
Iteration 3: Either
Iteration 4: Validated from cats

At the end of each iteration reflect. Do you like prefer way of doing error handling or the previous one? Why? Which are the tradeoffs of using different approaches?

Based on this: https://blog.ssanj.net/posts/2019-08-18-using-validated-for-error-accumulation-in-scala-with-cats.html
