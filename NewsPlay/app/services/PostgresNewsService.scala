package services
import models.{NewsArticle, Reporter, SearchParameters}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

class PostgresNewsService(implicit dbEngine: DatabaseEngine) extends BaseNewsService {

  override def InitialiseNewsArticles(): Future[Option[List[NewsArticle]]] = {
    null
  }

  override def GetNews(): Future[Seq[NewsArticle]] = {
    dbEngine.GetArticles()
  }

  override def GetNewsByID(articleID: Integer): Future[Option[NewsArticle]] = {
    null
  }

  override def GetNewsByReporterShortName(reporterName: String): Future[Seq[NewsArticle]] = {
    null
  }

  override def SearchArticles(searchParameters: SearchParameters): Future[Seq[NewsArticle]] = {
    null
  }

  override def GetListOfReporters(): Future[Seq[Reporter]] = {
    null
  }

  override def DeleteNewsByID(articleID: Integer): Future[Boolean] = {
    null
  }

  override def SearchArticlesByPattern(searchParameters: SearchParameters): Future[List[NewsArticle]] = {
    null
  }

}
