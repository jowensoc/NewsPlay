package services

import scala.collection.mutable.ListBuffer
import models.NewsArticle

object NewsService {

  private val listOfNewsArticles = initialiseArticles()

  def initialiseArticles(): ListBuffer[NewsArticle] = {
    var list = ListBuffer[NewsArticle]()

    list.addOne(new NewsArticle("Jamie wins trip to New York", "Grover", 1))
    list.addOne(new NewsArticle("Glasgow to host Eurovision", "Bert", 2))
    list.addOne(new NewsArticle("Marvel movie filming in Edinburgh", "Ernie", 3))
    list.addOne(new NewsArticle("Warm weather heads to Scotland", "Big Bird", 4))

    list.addOne(new NewsArticle("Asteriod hits local town. Everyone is fine", "Grover", 5))
    list.addOne(new NewsArticle("Toast always lands on buttered side, scientist finds", "Bert", 6))
    list.addOne(new NewsArticle("Cats are chaotic evil: Ernie opines", "Ernie", 7))
    list.addOne(new NewsArticle("Coffee stocks runs low in office. Chaos everywhere", "Big Bird", 8))

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

}
