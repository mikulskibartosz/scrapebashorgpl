package name.mikulskibartosz.bashorgpl

import io.circe.Json
import io.circe.generic.semiauto._
import io.circe.syntax._

object SerializeQuote {
  implicit val encoder = deriveEncoder[Quote]

  def apply(quote: Quote): Json = quote.asJson
}
