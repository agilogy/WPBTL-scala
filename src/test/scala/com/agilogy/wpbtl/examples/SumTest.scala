package com.agilogy.wpbtl.examples

import com.agilogy.wpbtl.Arbitrary
import com.agilogy.wpbtl.Checks.forAll
import com.agilogy.wpbtl.examples.Functions.sum
import com.agilogy.wpbtl.minitest.MiniTest.test

object SumTest extends App {

  test("Neutral element") {
    forAll(Arbitrary.int) { a =>
      val res1 = sum(a, 0)
      require(res1 == a, s"Expected sum to have zero as neutral element but sum($a,0) = $res1")
    }
  }

  test("Commutativity") {
    forAll(Arbitrary.int, Arbitrary.int) { (a, b) =>
      val res1 = sum(a, b)
      val res2 = sum(b, a)
      require(res1 == res2, s"Expected sum to be commutative but sum($a,$b) = $res1 and sum($b,$a) = $res2")
    }
  }

}
