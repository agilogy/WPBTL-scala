package com.agilogy.wpbtl.examples.test

import com.agilogy.wpbtl.examples.main._
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.immutable.ListMap

class JsonValueTest extends AnyFunSuite {

  test("foo") {
    val obj = JsonObject(
      ListMap(
        "a" -> JsonNumber("3"),
        "b" -> JsonString("3"),
        "c" -> JsonBoolean(true),
        "d" -> JsonNull,
        "e" -> JsonArray(
          List(
            JsonNumber("3"),
            JsonString("3"),
            JsonBoolean(true),
            JsonNull,
          )
        )
      )
    )

    println(obj.asString())
  }

}

