import TestFunctionsHelper.randomString

import scala.util.Random

object TestFunctions {

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
        case e: Throwable => throw PropertyFailedException(e.getMessage, v.toString, e, i)
      }
    }
  }

  def forAll[A, B](arb1: Arbitrary[A], arb2: Arbitrary[B])(f: (A, B) => Unit): Unit = {
    (1 to 100).foreach { i =>
      val v1 = arb1.nextValue()
      val v2 = arb2.nextValue()
      try {
        f(v1, v2)
      } catch {
        case e: Throwable =>
          val values = Seq(v1, v2).mkString(",")
          throw PropertyFailedException(e.getMessage, values, e, i)
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
    val string: Arbitrary[String] = Arbitrary(() => randomString())
  }

  def test(name: String)(f: => Unit) = try {
    f
    println(s"Test $name passed")
  } catch {
    case e: Throwable =>
      println(s"Test $name failed: ${e.getMessage}")
    //      e.printStackTrace()
  }

  case class PropertyFailedException(message: String, values: String, cause: Throwable, attempt: Int) extends Exception {
    override def getMessage: String = s"Property failed for values $values at attempt $attempt with message $message"
  }
}
