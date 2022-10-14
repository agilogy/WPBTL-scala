package com.agilogy.wpbtl.examples.test

import com.agilogy.wpbtl.Checks.forAll
import com.agilogy.wpbtl.{Arbitrary, PropertyFailedException}
import com.agilogy.wpbtl.examples.main.Functions.sum
import com.agilogy.wpbtl.examples.main.Rational
import org.scalatest.funsuite.AnyFunSuite

// TODO: Rationals
// 1. Design a case class for rationals
// 2. Implement sum
// 3. Check some properties of sum. You'll need an Arbitrary<Rational>.

class RationalTest extends AnyFunSuite {

  val rationalArb:Arbitrary[Rational] = Arbitrary.bind2(Arbitrary.int(-1000, 1000), Arbitrary.nonZeroInt(-1000, 1000)) {
    (a,b) => Rational(a,b)
  }

  test("Neutral element") {
      forAll(rationalArb, Arbitrary.nonZeroInt) { (a, b) =>
          val z = Rational(0, b)
        val res1 = a + z
          require(res1 == a, s"Expected sum to have zero as neutral element but sum($a,$z) = $res1")
      }
  }

  test("Commutativity") {
      forAll(rationalArb, rationalArb) { (a, b) =>
        val res1 = a+b
        val res2 = b+a
        require(res1 == res2, s"Expected sum to be commutative but sum($a,$b) = $res1 and sum($b,$a) = $res2")
      }
  }

}
