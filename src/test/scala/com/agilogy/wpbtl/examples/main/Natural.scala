package com.agilogy.wpbtl.examples.main

case class Natural private (value: Int)

object Natural{
  def apply(v: Int): Option[Natural] = v match {
    case x if x <= 0 => None
    case x => Some(new Natural(x))
  }
}