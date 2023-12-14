package services

import org.scalatest.funsuite.AnyFunSuiteLike

class DatabaseEngineTest extends AnyFunSuiteLike {

  test("Get all Articles") {
    var databaseEngine = new DatabaseEngine()

    val list = databaseEngine.GetArticles();

    assert(list.isCompleted)
  }

}
