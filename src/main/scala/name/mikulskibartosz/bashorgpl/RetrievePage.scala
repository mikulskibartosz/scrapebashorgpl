package name.mikulskibartosz.bashorgpl

import scala.concurrent.{ExecutionContext, Future}
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpResponse, _}
import akka.stream.ActorMaterializer
import akka.util.ByteString

class PageContent(val value: String) extends AnyVal

trait RetrievePage {
  def apply(pageUrl: PageUrl): Future[PageContent]
}

class RetrievePageUsingAkkaHttp(
                                 implicit actorSystem: ActorSystem,
                                 materializer: ActorMaterializer,
                                 ec: ExecutionContext
                               ) extends RetrievePage {
  override def apply(pageUrl: PageUrl): Future[PageContent] = {
    Http().singleRequest(HttpRequest(uri = pageUrl.value)).flatMap(httpResponseToPageContent)
  }

  private def httpResponseToPageContent(response: HttpResponse): Future[PageContent] = response match {
    case HttpResponse(StatusCodes.OK, _, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).map { body =>
        new PageContent(body.utf8String)
      }
    case resp @ HttpResponse(statusCode, _, _, _) =>
      resp.discardEntityBytes()
      Future.failed(new RuntimeException(s"Page not retrieved. Status code: $statusCode"))
  }
}