package services

import org.scalatest.funsuite.AnyFunSuiteLike

class NewsServiceTest extends AnyFunSuiteLike {


  test("Returns news articles") {
    val newsArticles = NewsService.GetNews();

    assert(newsArticles.nonEmpty)

  }

}
