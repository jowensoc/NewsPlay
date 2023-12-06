package services

import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuiteLike

class PostgresNewsServiceTest extends AnyFunSuiteLike with BeforeAndAfter {
  val newsService: PostgresNewsService = new PostgresNewsService();


  test("Get News By ID") {
    val newsID: Int = 1
    val results = newsService.GetNewsByID(newsID)

    assert(results.nonEmpty)

  }

  test("Get List Of Reporters") {
    val newsID: Int = 1
    val results = newsService.GetListOfReporters()

    assert(results.nonEmpty)
  }

  test("Get News By Reporter ShortName") {
    val results = newsService.GetListOfReporters()

    assert(results.nonEmpty)
  }

  test("Search Articles By Pattern") {
    assert(false)
  }

  test("Get News") {
    val results = newsService.GetNews()

    assert(results.nonEmpty)
  }

  test("Delete News By ID") {
    val newsID: Int = 1
    assert(newsService.DeleteNewsByID(newsID))
  }

  test("Search Articles") {
    assert(false)
  }

}
