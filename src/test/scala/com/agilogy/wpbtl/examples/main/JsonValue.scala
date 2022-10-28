package com.agilogy.wpbtl.examples.main

import scala.collection.immutable.ListMap

sealed trait JsonValue {
}

case class JsonBoolean(value: Boolean) extends JsonValue

case object JsonNull extends JsonValue

case class JsonString(value: String) extends JsonValue

case class JsonNumber(value: String) extends JsonValue

case class JsonArray(value: List[JsonValue]) extends JsonValue

case class JsonObject(value: ListMap[String, JsonValue]) extends JsonValue


object JsonValue {

  implicit class JsonValueOps(self: JsonValue) {
    def asString(): String = {
      self match {
        case JsonBoolean(value) => value.toString
        case JsonNull => "null"
        case JsonString(value) => s"\"$value\""
        case JsonNumber(value) => value
        case JsonArray(value) => value.map(_.asString()).mkString("[", ",", "]")
        case JsonObject(value) => value.map { case (k, v) => s"\"$k\":${v.asString()}" }.mkString("{", ",", "}")
      }
    }
  }

}
