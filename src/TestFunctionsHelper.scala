import scala.util.Random

object TestFunctionsHelper {

  def randomString(): String = {
    val length = 5 + (Random.nextInt() % 6)
    (1 to length).map{ _ =>
      val r = 97 + (Random.nextInt().abs % 26)
      r.toChar
    }.mkString("")
  }

}
