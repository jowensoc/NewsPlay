package services

import scala.collection.mutable.ListBuffer
import models.{NewsArticle, SearchParameters}

object NewsService {

  private val dataService = new NewsDataService("test")
  private val listOfNewsArticles = initialiseArticles()
  private var environmentName = ""

  def initialiseArticles(): ListBuffer[NewsArticle] = {
    dataService.InitialiseNewsArticles()
  }

  def GetNews(): List[NewsArticle] = {
    listOfNewsArticles.toList
  }

  def GetNewsByID(articleID: Int): Option[NewsArticle] = {
    listOfNewsArticles.toList.find(item => item.articleID == articleID)
  }

  def GetNewsByReporterShortName(reporterName: String): List[NewsArticle] = {
    listOfNewsArticles.toList.filter(item => item.reportShortName == reporterName)
  }

  def SearchArticles(searchParameters: SearchParameters): List[NewsArticle] = {
   listOfNewsArticles.filter(item => searchParameters.title.forall(_ == item.title)
      && searchParameters.reporterName.forall(_ == item.reporter)).toList
  }

  def SearchArticlesByPattern(searchParameters: SearchParameters): List[NewsArticle] = {
    listOfNewsArticles.filter(item => matchOptionPattern(searchParameters.title, item.title) && searchParameters.reporterName.forall(_ == item.reporter)).toList
  }

  private def matchOptionPattern(option: Option[String], keyword: String): Boolean = {
    option match {
      case Some(value) => keyword.contains(value)
      case None => false
      case _ => false
    }
  }

}
