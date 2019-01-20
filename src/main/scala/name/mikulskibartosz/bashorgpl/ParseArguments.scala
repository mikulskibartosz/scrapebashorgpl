package name.mikulskibartosz.bashorgpl

import scala.util.Try

class NumberOfPages(val value: Int) extends AnyVal

object ParseArguments {
  def apply(args: Array[String]): Try[NumberOfPages] = Try {
    args.headOption match {
      case Some(first) =>
        try {
          val numberOfPages = first.toInt
          if(numberOfPages > 0) {
            new NumberOfPages(numberOfPages)
          } else {
            throw new IllegalArgumentException("Number of pages must be a positive integer.")
          }
        } catch {
          case cause : NumberFormatException =>
            throw new IllegalArgumentException("Number of pages must be a positive integer.", cause)
        }
      case None =>
        throw new IllegalArgumentException("Number of pages should be passed in the application arguments.")
    }
  }
}
