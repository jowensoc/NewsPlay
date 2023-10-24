package services

import scala.collection.mutable.ListBuffer
import models.NewsArticle

object NewsService {

  private val listOfNewsArticles = initialiseArticles()

  def initialiseArticles(): ListBuffer[NewsArticle] = {
    var list = ListBuffer[NewsArticle]()

    list.addOne(new NewsArticle("Jamie wins trip to New York", "Grover"))
    list.addOne(new NewsArticle("Glasgow to host Eurovision", "Bert"))
    list.addOne(new NewsArticle("Marvel movie filming in Edinburgh", "Ernie"))
    list.addOne(new NewsArticle("Warm weather heads to Scotland", "Big Bird"))

    list
  }

  def GetNews(): List[NewsArticle] = {
    listOfNewsArticles.toList
  }

}
