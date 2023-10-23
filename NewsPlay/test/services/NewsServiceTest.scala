package services

import org.scalatest.funsuite.AnyFunSuiteLike

class NewsServiceTest extends AnyFunSuiteLike {


  test("Returns news articles") {
    val newsArticles = NewsService.GetNews();

    assert(newsArticles.nonEmpty)
  }

  test("Check if news contains this title") {
    val newsArticles = NewsService.GetNews();

    var expectedNewsTitle = "Marvel movie filming in Edinburgh"
    val results = newsArticles.find(item => item.toLowerCase() === expectedNewsTitle.toLowerCase())

    assert(results !== null)
  }

}
