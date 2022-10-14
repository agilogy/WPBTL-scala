# Homework


## 2022/10/14

### Basic

- Test `Rational` normalize properly when you `apply` the class. Spoiler by David: You want to test that multiplying
both numerator and denominator by the same integer (non-zero) value, you get the same normalized value you started with.
- Create a class `Natural` for [natural numbers](https://en.wikipedia.org/wiki/Natural_number) so that you can't possibly create an instance of `Natural` with a non positive value. Implement addition and multiplication.
- Write property based tests for `Natural`s addition.

### Intermediate

- Enhance `Rational.+` to support bigger `Int` values. Hint: Use mcmc. (by Adrià)
- Design a class Json that can represent strings, booleans, numbers, nulls, objects and arrays
- Implement a function `Json.asString()` that returns the string implementation of your Json and a function `Json.parse(s: String): Try[Json]` that tries to parse a string into an instance of `Json`.
- Write property based tests of `parse` and `asString`. You will need to define an `Arbitrary[Json]` and that will require combinators we still don't have. Try to generalize them properly to be used in other contexts. Hint: Generate random strings that do not contain quotes and random integers for numbers (don't try to make floating point arithmetic work here).

## 2022/10/07

### (Not so) Basic

- Implement a `Rational` class in `com.agillogy.wpbtl.examples.main` under `src/test` as a case class of the numerator and the denominator.
- Implement the operator `+` on `Rational`.
- Write property based tests of the sum of rationals. You'll need a way of creating an `Arbitrary[Rational]`. Use `pair` and `map` to build one. 

### Intermediate

- Rewrite your `Arbitrary[Rational]` to avoid the unnecessary creation of a `Tuple2`.
  - Clue: Create a new combinator not tied to Rational. See the `TODO` comments in the source code

- Create combinators like the one you created for `Rational` for arities 3 and 4.

- Rewrite the `pair` combinator to avoid code repetition with the ones you just wrote.

### Advanced

Remember: Advanced tasks won't be generally solved in this repository

- Somehow generalize the combinators you created to combine 2, 3 or 4 instances of `Arbitrary` to any number of instances. If you can't reason about the limits of such generalization.

## 2022/09/30

### Basic

- Separate example tests (which test `sum`) from the own library tests (which test `forAll`)
- Read these blog entries about testing:
  - [What is an automated test, again?](https://blog.agilogy.com/2022-05-27-what-is-an-automated-test-again.html)
  - [Testing and persistent state](https://blog.agilogy.com/2022-06-17-testing-and-persistent-state.html)
  - [Testing other side effects](https://blog.agilogy.com/2022-07-08-testing-other-side-effects.html)

### Intermediate

- Write property based tests of string concatenation. Think about what properties to test and write tests as usage examples of the library.

### Advanced

Remember: Advanced tasks won't be generally solved in this repository

- Write property based tests about `forAll`: "For all properties of integers, if I check that property, then the outcome has these properties." 
    - Clue: 
    
    ```scala
    forAll(intPropertyArb){
      val result = Try(forAll(arbInt){intPropertyArb})
      // Assertions about the result
    }
    ```