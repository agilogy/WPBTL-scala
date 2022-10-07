package com.agilogy.wpbtl.examples.test

import com.agilogy.wpbtl.Arbitrary
import com.agilogy.wpbtl.Checks.forAll
import com.agilogy.wpbtl.examples.main.Functions.concat
import org.scalatest.funsuite.AnyFunSuite

class StringTest extends AnyFunSuite {

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
