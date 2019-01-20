package name.mikulskibartosz.bashorgpl

import org.scalatest.{FlatSpec, Matchers}

class SerializeQuoteSpec extends FlatSpec with Matchers {
  "SerializeQuote" should "convert a quote to a JSON string" in {
    //given
    val quote = Quote(123, -2, "text")

    //when
    val result = SerializeQuote(quote).toString()

    //then
    result shouldEqual
      """{
        |  "id" : 123,
        |  "points" : -2,
        |  "content" : "text"
        |}""".stripMargin
  }
}
