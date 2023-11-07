package services

import models.{NewsArticle, Reporter}

import scala.collection.mutable.ListBuffer

trait DataService {
  def InitialiseNewsArticles() : ListBuffer[NewsArticle]
}

class NewsDataService(val environmentName: String) extends DataService {

  override def InitialiseNewsArticles(): ListBuffer[NewsArticle] = getNewsArticles

  private def getNewsArticles: ListBuffer[NewsArticle] = {
    val list = ListBuffer[NewsArticle]()

    if (environmentName.trim.toLowerCase() == "test") {
      list.addOne(new NewsArticle("Jamie wins trip to New York", "Grover", list.size + 1))
      list.addOne(new NewsArticle("Glasgow to host Eurovision", "Bert", list.size + 1))
      list.addOne(new NewsArticle("Marvel movie filming in Edinburgh", "Ernie", list.size + 1))
      list.addOne(new NewsArticle("Warm weather heads to Scotland", "Big Bird", list.size + 1))
      list.addOne(new NewsArticle("Asteroid hits local town. Everyone is fine", "Grover", list.size + 1))
      list.addOne(new NewsArticle("Toast always lands on buttered side, scientist finds", "Bert", list.size + 1))
      list.addOne(new NewsArticle("Cats are chaotic evil: Ernie opines", "Ernie", list.size + 1))
      list.addOne(new NewsArticle("Coffee stocks runs low in office. Chaos everywhere", "Big Bird", list.size + 1))
    }

    if (environmentName.trim.toLowerCase() == "live") {
      list.addOne(new NewsArticle("Glasgow couple wins trip to Canada", "Joe Bloggs", list.size + 1))
      list.addOne(new NewsArticle("Glasgow to host World Cycling Races", "Jane Bloggs", list.size + 1))
      list.addOne(new NewsArticle("York makes bid for Friendly City status", "Patrick Bloggs", list.size + 1))
      list.addOne(new NewsArticle("Poor weather hits Edinburgh", "Karen Bloggs", list.size + 1))

      list.addOne(new NewsArticle("Parades in London attracts tourists", "Joe Bloggs", list.size + 1))
      list.addOne(new NewsArticle("Film Festival launches in Wales", "Jane Bloggs", list.size + 1))
      list.addOne(new NewsArticle("Local town square gets face life", "Patrick Bloggs", list.size + 1))
      list.addOne(new NewsArticle("New shopping centre to be built", "Karen Bloggs", list.size + 1))
    }

    list
  }

  private def testNewsArticles(): ListBuffer[NewsArticle] = {
    val list = ListBuffer[NewsArticle]()

    list.addOne(new NewsArticle("Glasgow couple wins trip to Canada", "Joe Bloggs", list.size + 1))
    list.addOne(new NewsArticle("Glasgow to host World Cycling Races", "Jane Bloggs", list.size + 1))
    list.addOne(new NewsArticle("York makes bid for Friendly City status", "Patrick Bloggs", list.size + 1))
    list.addOne(new NewsArticle("Poor weather hits Edinburgh", "Karen Bloggs", list.size + 1))

    list.addOne(new NewsArticle("Parades in London attracts tourists", "Joe Bloggs", list.size + 1))
    list.addOne(new NewsArticle("Film Festival launches in Wales", "Jane Bloggs", list.size + 1))
    list.addOne(new NewsArticle("Local town square gets face life", "Patrick Bloggs", list.size + 1))
    list.addOne(new NewsArticle("New shopping centre to be built", "Karen Bloggs", list.size + 1))

    list
  }



}