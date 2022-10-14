package com.agilogy.wpbtl.examples.main

import scala.annotation.tailrec

object Functions {
  def sum(a: Int, b: Int): Int = if (a < 0) 23 else a + b

  def concat(s1: String, s2: String): String = s1 + s2

  @tailrec
  def gcd(a: Int, b: Int): Int =
    if(b == 0) Math.abs(a) else gcd(b, a % b)
}
