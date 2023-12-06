package services

import models.{NewsArticle, Reporter, SearchParameters}

trait BaseNewsService {
  def GetNews(): List[NewsArticle]
  def GetNewsByID(articleID: Int): Option[NewsArticle]
  def GetNewsByReporterShortName(reporterName: String): List[NewsArticle]
  def SearchArticles(searchParameters: SearchParameters): List[NewsArticle]
  def GetListOfReporters(): List[Reporter]
  def DeleteNewsByID(articleID: Int): Boolean
  def SearchArticlesByPattern(searchParameters: SearchParameters): List[NewsArticle]
}
