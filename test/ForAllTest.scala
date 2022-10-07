import TestFunctions.{Arbitrary, forAll, test}

import scala.util.Try

object ForAllTest extends App {
  test("ForAll throws exception when property fails") {
    val result = Try {
      forAll(Arbitrary.int) { a =>
        require(a == "a")
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

  test("Forall uses random numbers") {
    forAll(Arbitrary.int) { arbInt1 =>
      val result = Try(
        forAll(Arbitrary.int) { arbInt2 =>
          arbInt1 != arbInt2
        }
      )
      // Assercions sobre result
    }
  }
}
