package services

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}
import models.{NewsArticle, Reporter, SearchParameters}
import services.NewsService.{listOfNewsArticles, matchOptionPattern}

import scala.collection.mutable.ListBuffer

class TestNewsService extends BaseNewsService {
  implicit val ec: ExecutionContextExecutor = ExecutionContext.global
  private def getNewsArticles: ListBuffer[NewsArticle] = {
    val list = ListBuffer[NewsArticle]()

    list.addOne(new NewsArticle(list.size + 1, "Jamie wins trip to New York", "", 1, "Grover", ""))
    list.addOne(new NewsArticle(list.size + 1, "Glasgow to host Eurovision", "", 2, "Bert", ""))
    list.addOne(new NewsArticle(list.size + 1, "Marvel movie filming in Edinburgh", "", 3, "Ernie", ""))
    list.addOne(new NewsArticle(list.size + 1, "Warm weather heads to Scotland", "", 4, "Big", "Bird"))
    list.addOne(new NewsArticle(list.size + 1, "Asteroid hits local town. Everyone is fine", "", 1, "Grover", ""))
    list.addOne(new NewsArticle(list.size + 1, "Toast always lands on buttered side, scientist finds", "", 2, "Bert", ""))
    list.addOne(new NewsArticle(list.size + 1, "Cats are chaotic evil: Ernie opines", "", 3, "Ernie", ""))
    list.addOne(new NewsArticle(list.size + 1, "Coffee stocks runs low in office. Chaos everywhere", "", 4, "Big", "Bird"))
    list.addOne(new NewsArticle(list.size + 1, "UK Election is on in 2024", "", 1, "Grover", ""))

    list
  }

  private var listOfNewsArticles : ListBuffer[NewsArticle] = getNewsArticles

  override def InitialiseNewsArticles(): Future[Option[List[NewsArticle]]] = Future {
    var result: Option[List[NewsArticle]] = None

    if (listOfNewsArticles.nonEmpty) {
      result = Some(listOfNewsArticles.toList)
    }

    result
  }

  override def GetNews(): Future[Seq[NewsArticle]] = Future {
    listOfNewsArticles.toSeq
  }

  override def GetNewsByID(articleID: Integer): Future[Option[NewsArticle]] = Future {
    listOfNewsArticles.toSeq.find(item => item.articleID == articleID)
  }

  override def GetNewsByReporterShortName(reporterName: String): Future[Seq[NewsArticle]] = Future {
    listOfNewsArticles.toSeq.filter(item => item.reportShortName == reporterName)
  }

  override def SearchArticles(searchParameters: SearchParameters): Future[Seq[NewsArticle]] = Future {
    listOfNewsArticles.filter(item => searchParameters.title.forall(_ == item.title)
      && searchParameters.reporterName.forall(_ == item.reporterFullName)).toSeq
  }

  override def GetListOfReporters(): Future[Seq[Reporter]] = Future {
    listOfNewsArticles.sortBy(item => item.reporterFullName).distinctBy(item => item.reporterFullName).map(x => new Reporter(x.reporterFullName)).toSeq
  }

  override def DeleteNewsByID(articleID: Integer): Future[Boolean] = Future {
    val article = listOfNewsArticles.toList.find(item => item.articleID == articleID)

    if (article.isEmpty) {
      return Future.apply(false)
    }

    val idx = listOfNewsArticles.indexOf(article.get)
    listOfNewsArticles.remove(idx)

    true
  }

  override def SearchArticlesByPattern(searchParameters: SearchParameters): Future[Seq[NewsArticle]] = Future {
    listOfNewsArticles.filter(item => matchOptionPattern(searchParameters.title, item.title)
      && searchParameters.reporterName.forall(_ == item.reporterFullName)).toSeq
  }

  private def matchOptionPattern(option: Option[String], keyword: String): Boolean = {
    option match {
      case Some(value) => keyword.contains(value)
      case None => false
      case _ => false
    }
  }
}
