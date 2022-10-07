package com.agilogy.wpbtl

case class PropertyFailedException(message: String, values: String, cause: Throwable, attempt: Int) extends Exception {
  override def getMessage: String = s"Property failed for values $values at attempt $attempt with message $message"
}