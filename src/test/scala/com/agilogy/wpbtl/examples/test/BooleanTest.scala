package com.agilogy.wpbtl.examples.test

import com.agilogy.wpbtl.Arbitrary
import com.agilogy.wpbtl.Checks.forAll
import org.scalatest.funsuite.AnyFunSuite

class BooleanTest extends AnyFunSuite {
  test("Boolean double negation") {
    forAll(Arbitrary.boolean) { b1 =>
      require(!(!b1) == b1)
    }
  }
}
