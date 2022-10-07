import TestFunctions.{Arbitrary, forAll, test}

object BooleanTest extends App {
  test("Boolean double negation") {
    forAll(Arbitrary.boolean) { b1 =>
      require(!(!b1) == b1)
    }
  }
}
