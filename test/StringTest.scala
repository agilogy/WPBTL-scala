import Functions.{concat, sum}
import TestFunctions.{Arbitrary, forAll, test}

object StringTest extends App {

  test("Neutral element") {
    forAll(Arbitrary.string) { s1 =>
      require(concat(s1, "") == s1)
      require(concat("", s1) == s1)
    }
  }

  test("Length") {
    forAll(Arbitrary.string, Arbitrary.string) { (s1, s2) =>
      require(concat(s1, s2).length == s1.length + s2.length)
    }
  }

  test("Prefix & suffix") {
    forAll(Arbitrary.string, Arbitrary.string) { (s1, s2) =>
      require(concat(s1, s2).startsWith(s1))
      require(concat(s1, s2).endsWith(s2))
    }
  }
}

