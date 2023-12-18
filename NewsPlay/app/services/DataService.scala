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
      list.addOne(new NewsArticle(list.size + 1, "Jamie wins trip to New York", "",1, "Grover", ""))
      list.addOne(new NewsArticle(list.size + 1,"Glasgow to host Eurovision", "",2, "Bert", ""))
      list.addOne(new NewsArticle(list.size + 1,"Marvel movie filming in Edinburgh", "",3, "Ernie", ""))
      list.addOne(new NewsArticle(list.size + 1,"Warm weather heads to Scotland", "",4, "Big", "Bird"))
      list.addOne(new NewsArticle(list.size + 1,"Asteroid hits local town. Everyone is fine","",1,  "Grover", ""))
      list.addOne(new NewsArticle(list.size + 1,"Toast always lands on buttered side, scientist finds","",2,  "Bert", ""))
      list.addOne(new NewsArticle(list.size + 1,"Cats are chaotic evil: Ernie opines", "",3, "Ernie",""))
      list.addOne(new NewsArticle(list.size + 1,"Coffee stocks runs low in office. Chaos everywhere", "",4, "Big", ""))
    }

    if (environmentName.trim.toLowerCase() == "live") {
      list.addOne(new NewsArticle(list.size + 1, "Glasgow couple wins trip to Canada", "", 1, "Joe", "Bloggs"))
      list.addOne(new NewsArticle(list.size + 1, "Glasgow to host World Cycling Races", "", 2,"Jane", "Bloggs"))
      list.addOne(new NewsArticle(list.size + 1, "York makes bid for Friendly City status","", 3, "Patrick","Bloggs"))
      list.addOne(new NewsArticle(list.size + 1, "Poor weather hits Edinburgh","", 4, "Karen","Bloggs"))

      list.addOne(new NewsArticle(list.size + 1, "Parades in London attracts tourists","", 1, "Joe", "Bloggs"))
      list.addOne(new NewsArticle(list.size + 1, "Film Festival launches in Wales","", 2, "Jane", "Bloggs"))
      list.addOne(new NewsArticle(list.size + 1, "Local town square gets face life","", 3, "Patrick", "Bloggs"))
      list.addOne(new NewsArticle(list.size + 1, "New shopping centre to be built", "", 4,"Karen", "Bloggs"))
    }

    list
  }

  private def testNewsArticles(): ListBuffer[NewsArticle] = {
    val list = ListBuffer[NewsArticle]()

    list.addOne(new NewsArticle(list.size + 1, "Glasgow couple wins trip to Canada", "", 1, "Joe", "Bloggs"))
    list.addOne(new NewsArticle(list.size + 1, "Glasgow to host World Cycling Races", "", 2, "Jane", "Bloggs"))
    list.addOne(new NewsArticle(list.size + 1, "York makes bid for Friendly City status", "", 3, "Patrick", "Bloggs"))
    list.addOne(new NewsArticle(list.size + 1, "Poor weather hits Edinburgh", "", 4, "Karen", "Bloggs"))

    list.addOne(new NewsArticle(list.size + 1, "Parades in London attracts tourists", "", 1, "Joe", "Bloggs"))
    list.addOne(new NewsArticle(list.size + 1, "Film Festival launches in Wales", "", 2, "Jane", "Bloggs"))
    list.addOne(new NewsArticle(list.size + 1, "Local town square gets face life", "", 3, "Patrick", "Bloggs"))
    list.addOne(new NewsArticle(list.size + 1, "New shopping centre to be built", "", 4, "Karen", "Bloggs"))

    list
  }



}