package services

import models.NewsArticle
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

}
