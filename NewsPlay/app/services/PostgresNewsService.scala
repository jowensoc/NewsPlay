package services
import models.{NewsArticle, Reporter, SearchParameters}

class PostgresNewsService(implicit dbEngine: DatabaseEngine) extends BaseNewsService {
  override def GetNews(): List[NewsArticle] = {
    null
  }

  override def GetNewsByID(articleID: Int): Option[NewsArticle] = {
    dbEngine.
  }

  override def GetNewsByReporterShortName(reporterName: String): List[NewsArticle] = {
    null
  }

  override def SearchArticles(searchParameters: SearchParameters): List[NewsArticle] = {
    null
  }

  override def GetListOfReporters(): List[Reporter] = {
    null
  }

  override def DeleteNewsByID(articleID: Int): Boolean = {
    false
  }

  override def SearchArticlesByPattern(searchParameters: SearchParameters): List[NewsArticle] = {
    null
  }
}
