import Functions.sum
import TestFunctions.{Arbitrary, forAll, test, testMultipleTimes}

import scala.util.{Random, Try}

object SumTest extends App {

  test("Neutral element") {
    forAll(Arbitrary.int) { a =>
      val res1 = sum(a, 0)
      require(res1 == a, s"Expected sum to have zero as neutral element but sum($a,0) = $res1")
    }
  }

  test("Commutativity") {
    testMultipleTimes { () =>
      val a = Random.nextInt()
      val b = Random.nextInt()
      val res1 = sum(a, b)
      val res2 = sum(b, a)
      require(res1 == res2, s"Expected sum to be commutative but sum($a,$b) = $res1 and sum($b,$a) = $res2")
    }
  }

}
