package com.agilogy.wpbtl.examples.test

import com.agilogy.wpbtl.{Arbitrary, PropertyFailedException}
import com.agilogy.wpbtl.Checks.forAll
import com.agilogy.wpbtl.examples.main.Functions.sum
import org.scalatest.funsuite.AnyFunSuite

class SumTest extends AnyFunSuite {

  test("Neutral element") {
    intercept[PropertyFailedException] {
      forAll(Arbitrary.int) { a =>
        val res1 = sum(a, 0)
        assert(res1 == a, s"Expected sum to have zero as neutral element but sum($a,0) = $res1")
      }
    }
  }

  test("Commutativity") {
    intercept[PropertyFailedException] {
      forAll(Arbitrary.int, Arbitrary.int) { (a, b) =>
        val res1 = sum(a, b)
        val res2 = sum(b, a)
        assert(res1 == res2, s"Expected sum to be commutative but sum($a,$b) = $res1 and sum($b,$a) = $res2")
      }
    }
  }


}
