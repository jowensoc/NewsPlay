package services

import org.scalatest.funsuite.AnyFunSuiteLike
import play.api.db.slick.DatabaseConfigProvider

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class DatabaseEngineInjectTest @Inject()(protected val dbConfigProvider:DatabaseConfigProvider )(implicit ec: ExecutionContext) extends AnyFunSuiteLike{

  test("Get all Articles") {
    var databaseEngine = new DatabaseEngine(dbConfigProvider)

    val list = databaseEngine.GetArticles();

    assert(list.isCompleted)
  }

}
