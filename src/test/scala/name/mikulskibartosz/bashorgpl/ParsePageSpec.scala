package name.mikulskibartosz.bashorgpl

import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

class ParsePageSpec extends FlatSpec with Matchers {
  "ParsePage" should "return the first quote from the sample page" in {
    val pageContent =
      """
        |<div id="d4863008" class="q post">
        |<div class="bar">
        |<div class="right">
        |6 lipca 2018 16:02
        |</div>
        |
        |  <a class="qid click" href="/4863008/">#4863008</a>
        |  <a class="click votes rox" rel="nofollow" href="/rox/4863008/" style="visibility: hidden;">+</a>
        |  <span class=" points" style="visibility: visible;">-453</span>
        |  <a class="click votes sux" rel="nofollow" href="/sux/4863008/" style="visibility: hidden;">-</a><a class="fbshare" href="http://www.facebook.com/sharer.php?u=http%3A%2F%2Fbash.org.pl%2F4863008%2F&amp;t=%0A%09%09%09%09%3Ckuba%3E%20to%20w%20sumie%20teraz%20jestem%20fullstack%20developerem%0A%0A%3Cm%3E%20bardziej%20nullstack%20developerem%20%3B)%0A%09%09%09"></a>
        |  <span class="msg">&nbsp;</span>
        |</div>
        |<div class="quote post-content post-body">
        |&lt;kuba&gt; to w sumie teraz jestem fullstack developerem
        |<br>
        |&lt;m&gt; bardziej nullstack developerem ;)
        |</div>
        |</div>
      """.stripMargin
    //given
    val page = new PageContent(pageContent)

    //when
    val result = ParsePage(page)

    //then
    result.head shouldEqual Quote(
      id = 4863008,
      points = -453,
      content = "&lt;kuba&gt; to w sumie teraz jestem fullstack developerem \n<br> &lt;m&gt; bardziej nullstack developerem ;)"
    )
  }
}
