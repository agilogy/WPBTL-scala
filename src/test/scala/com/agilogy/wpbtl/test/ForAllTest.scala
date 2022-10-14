package com.agilogy.wpbtl.test

import com.agilogy.wpbtl.Arbitrary
import com.agilogy.wpbtl.Checks.forAll
import org.scalatest.funsuite.AnyFunSuite

import scala.util.Try

class ForAllTest extends AnyFunSuite {
  test("ForAll throws exception when property fails") {
    val result = Try {
      forAll(Arbitrary.int) { a => assert(a == 23) }
    }
    assert(result.isFailure)
  }

  test("ForAll passes when property is true") {
    val result = Try {
      forAll(Arbitrary.int) { _ =>
        assert(true)
      }
    }
    assert(result.isSuccess)
  }

}
