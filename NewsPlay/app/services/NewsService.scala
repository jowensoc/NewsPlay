package services

import scala.collection.mutable.ListBuffer
import models.{NewsArticle, SearchParameters}

object NewsService {

  private val listOfNewsArticles = initialiseArticles()

  def initialiseArticles(): ListBuffer[NewsArticle] = {
    var list = ListBuffer[NewsArticle]()

    list.addOne(new NewsArticle("Jamie wins trip to New York", "Grover", list.size + 1))
    list.addOne(new NewsArticle("Glasgow to host Eurovision", "Bert", list.size + 1))
    list.addOne(new NewsArticle("Marvel movie filming in Edinburgh", "Ernie", list.size + 1))
    list.addOne(new NewsArticle("Warm weather heads to Scotland", "Big Bird", list.size + 1))

    list.addOne(new NewsArticle("Asteriod hits local town. Everyone is fine", "Grover", list.size + 1))
    list.addOne(new NewsArticle("Toast always lands on buttered side, scientist finds", "Bert", list.size + 1))
    list.addOne(new NewsArticle("Cats are chaotic evil: Ernie opines", "Ernie", list.size + 1))
    list.addOne(new NewsArticle("Coffee stocks runs low in office. Chaos everywhere", "Big Bird", list.size + 1))

    list
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
   /* listOfNewsArticles.filter(item => searchParameters.title.forall(_ == item.title)
      && searchParameters.reporterName.forall(_ == item.reporter)).toList*/

    listOfNewsArticles.filter(item => searchParameters.title.forall(_ == item.title)
      && searchParameters.reporterName.forall(_ == item.reporter)).toList
  }

}
