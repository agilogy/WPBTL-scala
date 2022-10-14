package com.agilogy.wpbtl.examples.test

import com.agilogy.wpbtl.Arbitrary
import org.scalatest.funsuite.AnyFunSuite
import com.agilogy.wpbtl.Checks.forAll
import com.agilogy.wpbtl.examples.main.Functions.gcd

class GcdTest extends AnyFunSuite{

  private val boundedInt: Arbitrary[Int] = Arbitrary.int(-100, 100)

  test("gcd") {
    forAll(boundedInt, boundedInt) {(a,b) =>
      if(a != 0 || b != 0) {
        val res = gcd(a, b)
        assert(a % res == 0)
        assert(b % res == 0)
        assert(((res + 1) to Math.min(a, b)).forall(x => a % x != 0 || b % x != 0))
      }
    }
  }

  test( "gcd commutativity") {
    forAll(boundedInt, boundedInt) {(a,b) =>
      if(a != 0 || b != 0) assert(gcd(a, b) == gcd(b, a))
    }
  }

  test( "gcd is positive") {
    forAll(boundedInt, boundedInt) {(a,b) =>
      if(a != 0 || b != 0) assert(gcd(a, b) > 0)
    }
  }

}
