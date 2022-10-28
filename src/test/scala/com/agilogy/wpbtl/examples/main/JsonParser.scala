package com.agilogy.wpbtl.examples.main

import scala.util.{Failure, Success, Try}

object JsonParser {
  private val Digit = "[0-9]".r

  private def matchToken(s: String, token: String, index: Int): (Try[JsonValue], Int) = {
    ???
  }

  private def parse2(s:String, index: Int): (Try[JsonValue], Int) = {
    val substring = s.substring(index)
    substring match {
      case _ if substring.startsWith("null") => (Success(JsonNull), index + "null".length)
      case _ if substring.startsWith("true") => (Success(JsonBoolean(true)), index + "true".length)
      case _ if substring.startsWith("false") => (Success(JsonBoolean(false)), index + "false".length)
      case _ if substring.startsWith("[]") => (Success(JsonArray(List.empty)), index + "[]".length)
      case "" => (Failure(new IllegalArgumentException("Empty string")), index)
      case _ if substring.startsWith("-") || Digit.matches(substring.head.toString) =>
        (Success(JsonNumber(substring)), index + substring.length)
    }
  }

  def parse(s: String): Try[JsonValue] = {
    val (result, index) =  parse2(s, 0)

    result match{
      case Success(_) if index == s.length => result
      case Success(_) => Failure(new IllegalArgumentException(s"Unexpected token at $index"))
      case Failure(_) => result
    }

    /*
    val tokenStartObject = '{'
    val tokenEndObject = '}'
    val keyValueSep = ':'
    val  = ''*/
  }
}
