package name.mikulskibartosz.bashorgpl

import org.jsoup.Jsoup
import scala.collection.JavaConverters._

case class Quote(id: Int, points: Int, content: String)

object ParsePage {
  def apply(pageContent: PageContent): List[Quote] = {
    val document = Jsoup.parse(pageContent.value)
    val posts = document.select("div.post").asScala

    posts.map {
      post =>
        val id = post.selectFirst("a.qid").text()
        val score = post.selectFirst("span.points").text()
        val content = post.selectFirst("div.post-body").html()

        Quote(
          id = id.substring(1).toInt,
          points = score.toInt,
          content = content
        )
    }
  }.toList
}