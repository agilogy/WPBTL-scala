package com.agilogy.wpbtl.examples.test

import com.agilogy.wpbtl.examples.main.{JsonArray, JsonBoolean, JsonNull, JsonNumber, JsonParser, JsonValue}
import org.scalatest.funsuite.AnyFunSuite

import scala.util.{Failure, Success, Try}

class JsonParserTest extends AnyFunSuite{

  test("parse null"){
    assert( JsonParser.parse("null") == Success(JsonNull))
  }

  test("parse true") {
    assert(JsonParser.parse("true") == Success(JsonBoolean(true)))
  }

  test("parse false") {
    assert(JsonParser.parse("false") == Success(JsonBoolean(false)))
  }

  test("parse number") {
    assert(JsonParser.parse("3") == Success(JsonNumber("3")))
  }

  test("empty string") {
    assert(JsonParser.parse("").isFailure)
  }

  test("empty array"){
    assert(JsonParser.parse("[]") == Success(JsonArray(List.empty)) )
  }

  test("failure"){
    val res: Try[JsonValue] = JsonParser.parse("nullhola")
    res match{
      case Failure(t) => assert(t.getMessage == s"Unexpected token at 4")
      case Success(_) => fail()
    }
  }

  /*
  test("number array") {
    assert(JsonParser.parse("[1,2]") == Success(JsonArray(List(JsonNumber("1"), JsonNumber("2")))))
  }*/

}
