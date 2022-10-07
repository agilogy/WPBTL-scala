package com.agilogy.wpbtl.test

import com.agilogy.wpbtl.Arbitrary
import com.agilogy.wpbtl.Checks.forAll
import org.scalatest.funsuite.AnyFunSuite

import scala.util.Try

class ForAllTest extends AnyFunSuite {
  test("ForAll throws exception when property fails") {
    val result = Try {
      forAll(Arbitrary.int) { a =>
        require(a == 23)
      }
    }
    require(result.isFailure)
  }

  test("ForAll passes when property is true") {
    val result = Try {
      forAll(Arbitrary.int) { _ =>
        require(true)
      }
    }
    require(result.isSuccess)
  }

}
