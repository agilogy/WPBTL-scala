package com.agilogy.wpbtl.examples

import com.agilogy.wpbtl.Arbitrary
import com.agilogy.wpbtl.Checks.forAll
import com.agilogy.wpbtl.examples.Functions.concat
import com.agilogy.wpbtl.minitest.MiniTest.test

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
