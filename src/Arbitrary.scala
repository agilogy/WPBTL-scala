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
  val boolean: Arbitrary[Boolean] = Arbitrary(() => Random.nextBoolean())

  val string: Arbitrary[String] = Arbitrary { () =>
    val length = 5 + (Random.nextInt() % 6)
    (1 to length).map { _ =>
      val r = 97 + (Random.nextInt().abs % 26)
      r.toChar
    }.mkString("")
  }

  val rational = ???
  def bind = ???
  def bindN = ???

  def int(min: Int = 0, max: Int): Arbitrary[Int] = Arbitrary(() => Random.between(min, max))

  def option[T](arb: Arbitrary[T], noneProbabilityPercent: Int = 50): Arbitrary[Option[T]] = {
    require(noneProbabilityPercent <= 100 && noneProbabilityPercent >= 0)
    int(max = 100).map {
      case i if i > noneProbabilityPercent => Some(arb.nextValue())
      case _ => None
    }
  }

  def pair[A, B](arbA: Arbitrary[A], arbB: Arbitrary[B]): Arbitrary[(A, B)] =
    Arbitrary(() => (arbA.nextValue(), arbB.nextValue()))




}