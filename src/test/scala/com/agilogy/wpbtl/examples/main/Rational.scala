package com.agilogy.wpbtl.examples.main

import com.agilogy.wpbtl.examples.main.Functions.gcd


case class Rational private(numerator: Int, denominator: Int) {
  def +(other: Rational): Rational = Rational.apply(this.numerator*other.denominator + other.numerator*this.denominator, other.denominator*this.denominator)
}

object Rational {
  def apply(numerator: Int, denominator: Int): Rational = {
    val d = gcd(numerator, denominator)
    val m = if(denominator < 0) -1 else 1
    new Rational(numerator/d*m, denominator/d*m)
  }
}

