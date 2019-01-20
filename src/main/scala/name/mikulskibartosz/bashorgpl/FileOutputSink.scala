package name.mikulskibartosz.bashorgpl

import java.io.{File, FileWriter, PrintWriter}

import akka.Done
import akka.stream.scaladsl.{Flow, Keep, Sink}
import io.circe.Json

import scala.concurrent.Future

class FileOutputSink(file: File, countItems: CountItems) {
  val fileWriter = new FileWriter(file)
  val printWriter = new PrintWriter(fileWriter)

  def sink(): Sink[Json, Future[Done]] = {
    printWriter.println("[")

    Flow.fromFunction[Json, String](_.toString()).toMat(Sink.foreach{ row =>
      printWriter.println(s"$row,")
      countItems.add()
    })(Keep.right)
  }

  def close(): Unit = {
    printWriter.println("]")

    printWriter.flush()
    printWriter.close()
  }
}
