package com.agilogy.wpbtl.examples

import com.agilogy.wpbtl.Arbitrary
import com.agilogy.wpbtl.Checks.forAll
import com.agilogy.wpbtl.minitest.MiniTest.test

object BooleanTest extends App {
  test("Boolean double negation") {
    forAll(Arbitrary.boolean) { b1 =>
      require(!(!b1) == b1)
    }
  }
}
