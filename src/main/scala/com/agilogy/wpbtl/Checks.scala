package com.agilogy.wpbtl

object Checks {

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

}
