package name.mikulskibartosz.bashorgpl

import org.scalatest.{FlatSpec, Matchers}

class GeneratePageUrlsSpec extends FlatSpec with Matchers {
  "GeneratePageUrls" should "return an url which points to the last page" in {
    //given
    val baseUrl = "http://bash.org.pl/latest/?page="
    val pageNumber = new NumberOfPages(1)
    val objectUnderTest = new GeneratePageUrls(baseUrl)

    //when
    val urls = objectUnderTest(pageNumber)

    //then
    urls shouldEqual List(
      new PageUrl("http://bash.org.pl/latest/?page=1")
    )
  }

  it should "return urls of the n given pages with the most recent quotes" in {
    //given
    val baseUrl = "http://bash.org.pl/latest/?page="
    val pageNumber = new NumberOfPages(4)
    val objectUnderTest = new GeneratePageUrls(baseUrl)

    //when
    val urls = objectUnderTest(pageNumber)

    //then
    urls shouldEqual List(
      new PageUrl("http://bash.org.pl/latest/?page=1"),
      new PageUrl("http://bash.org.pl/latest/?page=2"),
      new PageUrl("http://bash.org.pl/latest/?page=3"),
      new PageUrl("http://bash.org.pl/latest/?page=4")
    )
  }
}
