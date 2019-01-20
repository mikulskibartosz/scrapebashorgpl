package name.mikulskibartosz.bashorgpl

class PageUrl(val value: String) extends AnyVal

class GeneratePageUrls(val baseUrl: String) {
  def apply(numberOfPages: NumberOfPages): List[PageUrl] = Range.inclusive(1, numberOfPages.value).map(pageNumber =>
    new PageUrl(s"$baseUrl$pageNumber")
  ).toList
}
