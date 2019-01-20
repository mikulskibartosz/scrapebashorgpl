package name.mikulskibartosz.bashorgpl

import java.io.File
import java.time.{Duration, Instant}

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object Main extends App {
  ParseArguments(args) match {
    case Success(numberOfPages) =>
      val startTime = Instant.now

      implicit val actorSystem: ActorSystem = ActorSystem()
      implicit val actorMaterializer: ActorMaterializer = ActorMaterializer()
      implicit val ec: ExecutionContextExecutor = actorSystem.dispatcher

      val generatePageUrls = new GeneratePageUrls("http://bash.org.pl/latest/?page=")
      val retrievePage = new RetrievePageUsingAkkaHttp()
      val countItems = new CountItems()

      val config = ConfigFactory.load()
      val sink = new FileOutputSink(new File(config.getString("outputFile")), countItems)

      val stream = new ScrapeBashOrgPl(retrievePage, generatePageUrls, sink, 4)

      stream(numberOfPages).onComplete{
        case Failure(ex) =>
          Console.println(s"Error while processing quotes: $ex")

        case Success(_) =>
          sink.close()

          val endTime = Instant.now()
          val duration = Duration.between(startTime, endTime)
          val retrievedQuotes = countItems.numberOfItems

          Console.println(s"Number of retrieved quotes: $retrievedQuotes")
          Console.println(s"Average elapsed time per page: ${duration.getSeconds / numberOfPages.value.toDouble} seconds")
          Console.println(s"Average elapsed time per quote: ${duration.getSeconds / retrievedQuotes.toDouble} seconds")

          actorSystem.terminate()
      }
    case Failure(ex) =>
      Console.println(s"You must pass the number of pages as an application argument. Error: $ex")
  }
}
