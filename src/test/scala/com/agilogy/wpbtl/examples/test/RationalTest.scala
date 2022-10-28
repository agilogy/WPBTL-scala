package com.agilogy.wpbtl.examples.test

import com.agilogy.wpbtl.Checks.forAll
import com.agilogy.wpbtl.{Arbitrary, PropertyFailedException}
import com.agilogy.wpbtl.examples.main.Functions.sum
import com.agilogy.wpbtl.examples.main.Rational
import org.scalatest.funsuite.AnyFunSuite

class RationalTest extends AnyFunSuite {

  private val boundedInt: Arbitrary[Int] = Arbitrary.int(-1000, 1000)

  val rationalArb: Arbitrary[Rational] = Arbitrary.bind2(boundedInt, boundedInt)(Rational.apply)

  test("Neutral element") {
    forAll(rationalArb, Arbitrary.nonZeroInt) { (a, zeroDenominator) =>
      val z = Rational(0, zeroDenominator)
      val res1 = a + z
      assert(res1 == a, s"Expected sum to have zero as neutral element but sum($a,$z) = $res1")
    }
  }

  test("Commutativity") {
    forAll(rationalArb, rationalArb) { (a, b) =>
      val res1 = a + b
      val res2 = b + a
      assert(res1 == res2, s"Expected sum to be commutative but sum($a,$b) = $res1 and sum($b,$a) = $res2")
    }
  }

  test("Normalization") {
    forAll(rationalArb, Arbitrary.boundedNonZeroInt(-1000, 1000)) { (a, multiplier) =>
      val b = Rational(a.numerator*multiplier, a.denominator*multiplier)
      assert(a == b, s"Expected rational numbers to be equal but $a != $b")
    }
  }

}
