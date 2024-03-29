package services

import scala.collection.mutable.ListBuffer
import models.{NewsArticle, Reporter, SearchParameters}

object NewsService {

  //private val dataService = new NewsDataService("test")
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
      && searchParameters.reporterName.forall(_ == item.reporterFullName)).toList
  }

  def GetListOfReporters(): List[Reporter] = {
    listOfNewsArticles.sortBy(item => item.reporterFullName).distinctBy(item => item.reporterFullName).map(x => new Reporter(x.reporterID, x.reporterFirstName, x.reporterLastName)).toList
  }

  def DeleteNewsByID(articleID: Int): Boolean = {
    val article = listOfNewsArticles.toList.find(item => item.articleID == articleID)

    if (article.isEmpty) {
      return false
    }

    val idx = listOfNewsArticles.indexOf(article.get)
    listOfNewsArticles.remove(idx)

    true
  }

  def SearchArticlesByPattern(searchParameters: SearchParameters): List[NewsArticle] = {
    listOfNewsArticles.filter(item => matchOptionPattern(searchParameters.title, item.title) && searchParameters.reporterName.forall(_ == item.reporterFullName)).toList
  }

  private def matchOptionPattern(option: Option[String], keyword: String): Boolean = {
    option match {
      case Some(value) => keyword.contains(value)
      case None => false
      case _ => false
    }
  }

}
