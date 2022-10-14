package com.agilogy.wpbtl.examples.test

import com.agilogy.wpbtl.Arbitrary
import org.scalatest.funsuite.AnyFunSuite
import com.agilogy.wpbtl.Checks.forAll
import com.agilogy.wpbtl.examples.main.Functions.gcd

class GcdTest extends AnyFunSuite{

  test("gcd") {
    forAll(Arbitrary.int(-100,100), Arbitrary.int(-100,100)) {(a,b) =>
      if(a != 0 || b != 0) {
        val res = gcd(a, b)
        assert(a % res == 0)
        assert(b % res == 0)
        assert(((res + 1) to Math.min(a, b)).forall(x => a % x != 0 || b % x != 0))
      }
    }
  }

  test( "gcd commutativity") {
    forAll(Arbitrary.int(-100,100), Arbitrary.int(-100,100)) {(a,b) =>
      if(a != 0 || b != 0) assert(gcd(a, b) == gcd(b, a))
    }
  }

  test( "gcd is positive") {
    forAll(Arbitrary.int(-100,100), Arbitrary.int(-100,100)) {(a,b) =>
      if(a != 0 || b != 0) assert(gcd(a, b) > 0)
    }
  }

}
