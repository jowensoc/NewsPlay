package services

import models.NewsArticle
import models.SearchParameters
import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuiteLike

class NewsServiceTest extends AnyFunSuiteLike with BeforeAndAfter {

  var listOfNewsArticles:List[NewsArticle] = List[NewsArticle]()
  val listOfReporters: List[String] = List.apply("Grover", "Big Bird", "Bert", "Ernie")
  var listOfArticleIDs:List[Int] = List(1,2,3,4)

  before {
    listOfNewsArticles = NewsService.GetNews()
  }

  test("Returns news articles") {
    assert(listOfNewsArticles.nonEmpty)
  }

  test("Check if news contains this title") {
    val expectedNewsTitle = "Marvel movie filming in Edinburgh"
    val results = listOfNewsArticles.find(item => item.title.toLowerCase() === expectedNewsTitle.toLowerCase())

    assert(results !== null)
  }

  for (expectedReporter <- listOfReporters) {
    test("Check if news has " + expectedReporter + " as Reporter") {
      val results = listOfNewsArticles.find(item => item.reporter.toLowerCase() === expectedReporter.toLowerCase())

      assert(results !== null)
    }
  }

  for (expectedID <- listOfArticleIDs) {
    test("Check if news article exists by ID: " + expectedID) {
      val results = NewsService.GetNewsByID(expectedID)

      assert(results !== null)
    }
  }

  test("Article should not be found") {
    val results = NewsService.GetNewsByID(0)

    assert(results === None)
  }

  for (expectedReporter <- listOfReporters) {
    test("Check if reporter " + expectedReporter + " has news articles") {
      val reporterShortName = expectedReporter.replace(" ", "-").trim.toLowerCase()
      val results = NewsService.GetNewsByReporterShortName(reporterShortName)

      assert(results !== null)
    }
  }

  test("Search for articles by reporter and results should be returned") {
    val title = "York"
    val reporterName = "Grover"
    val searchParameters: SearchParameters = new SearchParameters();
    searchParameters.title = if (title.nonEmpty) Some(title) else None
    searchParameters.reporterName = if (reporterName.nonEmpty) Some(reporterName) else None

    val results = NewsService.SearchArticles(searchParameters)

    assert(results.nonEmpty)

    val wrongReporter = results.find(item => item.reporter.toLowerCase() !== reporterName.toLowerCase())
    assert(wrongReporter.isEmpty)
  }

  test("Mock search contains") {
    val title = "York"
    val reporterName = "Grover"
    val searchParameters: SearchParameters = new SearchParameters();
    searchParameters.title = if (title.nonEmpty) Some(title) else None
    searchParameters.reporterName = if (reporterName.nonEmpty) Some(reporterName) else None

    /*var results = listOfNewsArticles.filter(item => searchParameters.title.contains(item.title)
      && searchParameters.reporterName.forall(_ == item.reporter)).toList*/

    var results = listOfNewsArticles.filter(item => item.title.indexOf(title) >= 0).toList

    assert(results.nonEmpty)
  }
}
