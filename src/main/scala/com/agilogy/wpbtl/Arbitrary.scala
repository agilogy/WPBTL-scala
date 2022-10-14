package com.agilogy.wpbtl

import scala.util.Random

case class Arbitrary[A](nextValue: () => A) {
  def map[B](f: A => B): Arbitrary[B] = Arbitrary(() => f(this.nextValue()))

}

object Arbitrary {

  val int: Arbitrary[Int] = Arbitrary({
    () =>
      val a = Random.nextInt()
      if (a % 10 < 5) a % 10 else a
  })

  def int(min: Int = 0, max: Int): Arbitrary[Int] = Arbitrary(() => Random.between(min, max))

  val nonZeroInt: Arbitrary[Int] = int.map {
    case 0 => 1
    case n => n
  }

  def nonZeroInt(min: Int, max: Int): Arbitrary[Int] = int(min, max).map {
    case 0 => 1
    case n => n
  }

  val boolean: Arbitrary[Boolean] = Arbitrary(() => Random.nextBoolean())

  val string: Arbitrary[String] = Arbitrary { () =>
    (1 to 5 + (Random.nextInt() % 6)).map { _ => (97 + (Random.nextInt().abs % 26)).toChar }.mkString("")
  }

  def bind2[A, B, C](a: Arbitrary[A], b: Arbitrary[B])(f: (A, B) => C): Arbitrary[C] =
    Arbitrary(() => f(a.nextValue(), b.nextValue()))

  def pair[A, B](arbA: Arbitrary[A], arbB: Arbitrary[B]): Arbitrary[(A, B)] = bind2(arbA, arbB)(Tuple2.apply)

  def bind3[A, B, C, D](a: Arbitrary[A], b: Arbitrary[B], c: Arbitrary[C])(f: (A, B, C) => D): Arbitrary[D] =
    Arbitrary(() => f(a.nextValue(), b.nextValue(), c.nextValue()))

  // TODO: EXPERT: Generalize bind to any arity (somehow, may be a builder), but don't use cats or other external libraries.
  def bindN = ???

  def option[T](arb: Arbitrary[T], noneProbabilityPercent: Int = 50): Arbitrary[Option[T]] = {
    assert(noneProbabilityPercent <= 100 && noneProbabilityPercent >= 0)
    int(max = 100).map {
      case i if i > noneProbabilityPercent => Some(arb.nextValue())
      case _ => None
    }
  }

}