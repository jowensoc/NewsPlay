package services

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}
import models.{NewsArticle, Reporter, SearchParameters}



trait BaseNewsService {
  def InitialiseNewsArticles(): Future[Option[List[NewsArticle]]]
  def GetNews(): Future[Seq[NewsArticle]]
  def GetNewsByID(articleID: Integer): Future[Option[NewsArticle]]
  def GetNewsByReporterShortName(reporterName: String): Future[Seq[NewsArticle]]
  def SearchArticles(searchParameters: SearchParameters): Future[Seq[NewsArticle]]
  def GetListOfReporters(): Future[Seq[Reporter]]
  def DeleteNewsByID(articleID: Integer): Future[Boolean]
  def SearchArticlesByPattern(searchParameters: SearchParameters): Future[Seq[NewsArticle]]
}
