package name.mikulskibartosz.bashorgpl

import akka.Done
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source

import scala.concurrent.Future

class ScrapeBashOrgPl(retrievePage: RetrievePage, generatePageUrls: GeneratePageUrls, sink: FileOutputSink, parallelism: Int)(implicit actorMaterializer: ActorMaterializer) {
  def apply(numberOfPages: NumberOfPages): Future[Done] = {
    Source.single(numberOfPages)
      .mapConcat[PageUrl](generatePageUrls(_))
      .mapAsync(parallelism)(retrievePage.apply)
      .mapConcat(ParsePage.apply)
      .map(SerializeQuote.apply)
      .runWith(sink.sink())
  }
}
