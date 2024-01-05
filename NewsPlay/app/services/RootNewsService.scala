package services

import models.{NewsArticle, Reporter, SearchParameters}

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

class RootNewsService(implicit dbEngine: DatabaseEngine) {

  private val dataService: BaseNewsService = new PostgresNewsService()
  private val listOfNewsArticles = initialiseArticles()

  def initialiseArticles(): Future[Option[List[NewsArticle]]] = {
    dataService.InitialiseNewsArticles()
  }

  def GetNews(): Future[Seq[NewsArticle]] = {
    dataService.GetNews()
  }

  def GetNewsByID(articleID: Int): Future[Option[NewsArticle]] = {
    dataService.GetNewsByID(articleID)
  }

  def GetNewsByReporterShortName(reporterName: String): Future[Seq[NewsArticle]] = {
    dataService.GetNewsByReporterShortName(reporterName)
  }

  def SearchArticles(searchParameters: SearchParameters): Future[Seq[NewsArticle]] = {
    dataService.SearchArticles(searchParameters)
  }

  def GetListOfReporters(): Future[Seq[Reporter]] = {
    dataService.GetListOfReporters()
  }

  def DeleteNewsByID(articleID: Int): Future[Boolean] = {
    dataService.DeleteNewsByID(articleID)
  }

  def SearchArticlesByPattern(searchParameters: SearchParameters): Future[Seq[NewsArticle]] = {
    dataService.SearchArticlesByPattern(searchParameters)
  }

}
