package services

import models.NewsArticle
import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuiteLike

class NewsServiceTest extends AnyFunSuiteLike with BeforeAndAfter {

  var listOfNewsArticles:List[NewsArticle] = List[NewsArticle]()
  val listOfAuthors: List[String] = List.apply("Grover", "Big Bird", "Bert", "Ernie")

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

  for (expectedAuthor <- listOfAuthors) {
    test("Check if news has " + expectedAuthor + " as Author") {
      val results = listOfNewsArticles.find(item => item.author.toLowerCase() === expectedAuthor.toLowerCase())

      assert(results !== null)
    }
  }


}
