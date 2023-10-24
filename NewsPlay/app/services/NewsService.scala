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

    list
  }

  def GetNews(): List[NewsArticle] = {
    listOfNewsArticles.toList
  }

  def GetNewsByID(articleID: Int): Option[NewsArticle] = {
    listOfNewsArticles.toList.find(item => item.articleID == articleID)
  }

}
