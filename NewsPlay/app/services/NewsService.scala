package services

import scala.collection.mutable.ListBuffer

object NewsService {

  private val listOfNewsArticles = initialiseArticles()

  def initialiseArticles(): ListBuffer[String] = {
    var list = ListBuffer[String]()

    list.addOne("Jamie wins trip to New York")
    list.addOne("Glasgow to host Eurovision")
    list.addOne("Marvel movie filming in Edinburgh")
    list.addOne("Warm weather heads to Scotland")

    list
  }

  def GetNews(): List[String] = {
    listOfNewsArticles.toList
  }

}
