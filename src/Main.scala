import Functions.sum
import FunctionsTest.{Arbitrary, forAll, test, testMultipleTimes}

import scala.util.{Random, Try}

object Functions {
  def sum(a: Int, b: Int): Int = if (a < 0) 23 else a + b

}

object FunctionsTest {

  def testMultipleTimes(f: () => Unit): Unit = {
    (1 to 100).foreach { _ =>
      f()
    }
  }

  def forAll[A](arb: Arbitrary[A])(f: A => Unit): Unit = {
    (1 to 100).foreach { i =>
      val v = arb.nextValue()
      try {
        f(v)
      } catch {
        case e: Throwable => throw PropertyFailedException(e.getMessage, v, e, i)
      }
    }
  }

  case class Arbitrary[A](nextValue: () => A)

  object Arbitrary {
    val int: Arbitrary[Int] = Arbitrary({
      () =>
        val a = Random.nextInt()
        if(a % 10 < 5) a % 10 else a
    })
    val boolean: Arbitrary[Boolean] = Arbitrary(() => Random.nextBoolean())
  }

  def test(name: String)(f: => Unit) = try {
    f
    println(s"Test $name passed")
  } catch {
    case e: Throwable =>
      println(s"Test $name failed: ${e.getMessage}")
//      e.printStackTrace()
  }

  case class PropertyFailedException[A](message: String, value: A, cause: Throwable, attempt: Int) extends Exception {
    override def getMessage: String = s"Property failed for value $value at attempt $attempt with message $message"
  }
}

object Main extends App {

  test("Commutativity") {
    testMultipleTimes { () =>
      val a = Random.nextInt()
      val b = Random.nextInt()
      val res1 = sum(a, b)
      val res2 = sum(b, a)
      require(res1 == res2, s"Expected sum to be commutative but sum($a,$b) = $res1 and sum($b,$a) = $res2")
    }
  }

  test("Neutral element") {
    forAll(Arbitrary.int) { a =>
      val res1 = sum(a, 0)
      require(res1 == a, s"Expected sum to have zero as neutral element but sum($a,0) = $res1")
    }
  }

  test("Boolean double negation") {
    forAll(Arbitrary.boolean) { b1 =>
      require(!(!b1) == b1)
    }
  }

  test("ForAll throws exception when property fails") {
    val result = Try {
      forAll(Arbitrary.int){ a =>
        require(a == 0)
      }
    }
    require(result.isFailure)
  }

  test("ForAll passes when property is true") {
    val result = Try {
      forAll(Arbitrary.int){ a =>
        require(true)
      }
    }
    require(result.isSuccess)
  }

  test("Test division") {

    forAll(Arbitrary.int){ a =>
      require(2/a < 2)
    }

  }



}
