package com.agilogy.wpbtl.minitest

object MiniTest {

  def test(name: String)(f: => Unit): Unit = try {
    f
    println(s"Test $name passed")
  } catch {
    case e: Throwable =>
      println(s"Test $name failed: ${e.getMessage}")
  }

}
