package services

import org.scalatest.funsuite.AnyFunSuiteLike

class NewsServiceTest extends AnyFunSuiteLike {


  test("Returns news articles") {
    val newsArticles = NewsService.GetNews();

    assert(newsArticles.nonEmpty)
  }

  test("Check if news contains this title") {
    val newsArticles = NewsService.GetNews();

    val expectedNewsTitle = "Marvel movie filming in Edinburgh"
    val results = newsArticles.find(item => item.title.toLowerCase() === expectedNewsTitle.toLowerCase())

    assert(results !== null)
  }

  test("Check if news has Grover as Author") {
    val newsArticles = NewsService.GetNews();

    val expectedAuthor = "Grover"
    val results = newsArticles.find(item => item.author.toLowerCase() === expectedAuthor.toLowerCase())

    assert(results !== null)
  }

}
