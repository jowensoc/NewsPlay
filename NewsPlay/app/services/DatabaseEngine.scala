package services

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import models.{NewsArticle, Reporter}
import slick.lifted.{MappedProjection, ProvenShape}

@Singleton
class DatabaseEngine @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  class NewsArticles(tag: Tag) extends Table[NewsArticle](tag, "NewsArticle") {

    def ArticleID = column[Int]("ArticleID", O.PrimaryKey, O.AutoInc)
    def Title = column[String]("Title")
    def ArticleContent = column[String]("ArticleContent")
    def ReporterID = column[Int]("ReporterID")

    def ReporterFirstName = ""
    def ReporterLastName = ""
    def * = (ArticleID, Title, ArticleContent, ReporterID, ReporterFirstName, ReporterLastName) <> ((NewsArticle.apply _).tupled, NewsArticle.unapply)
  }

  class Reporters(tag: Tag) extends Table[Reporter](tag, "Reporter") {

    def ReporterID = column[Int]("ReporterID", O.PrimaryKey, O.AutoInc)

    def FirstName = column[String]("FirstName")
    def LastName = column[String]("LastName")

    def * = (ReporterID, FirstName, LastName) <> ((Reporter.apply _).tupled, Reporter.unapply)
  }

  private val newsArticles = TableQuery[NewsArticles]
  private val reporters = TableQuery[Reporters]

  private def createQuery():  Query[MappedProjection[NewsArticle, (Int, String, String, Int, String, String)], NewsArticle, Seq]= {
    val f = newsArticles.join(reporters).on(_.ReporterID === _.ReporterID).map {
      case (n, r) => (n.ArticleID,
        n.Title,
        n.ArticleContent,
        r.ReporterID,
        r.FirstName,
        r.LastName) <> (NewsArticle.tupled, NewsArticle.unapply)
    }

    f
  }

  def GetArticles(): Future[Seq[NewsArticle]] = {
    /*val f = newsArticles.join(reporters).on (_.ReporterID === _.ReporterID).map {
      case (n, r) => (n.ArticleID,
        n.Title,
        n.ArticleContent,
        r.ReporterID,
        r.FirstName,
        r.LastName) <> (NewsArticle.tupled, NewsArticle.unapply)
    }*/

    val f = createQuery()

    db.run(f.result)
  }

  private def articleToArticleRow(article: NewsArticle): NewsArticle = {
    NewsArticle(article.articleID, article.title, article.articleContent, article.reporterID, article.reporterFirstName, article.reporterLastName)
  }

  private def toArticleDetails(article: NewsArticle, reporter: Reporter): NewsArticle = {
    NewsArticle(article.articleID, article.title, article.articleContent, reporter.reporterID, reporter.firstName, reporter.lastName)
  }

  def GetReporters(): Future[Seq[Reporter]] = {
    val f = db.run(reporters.sortBy(x => (x.LastName, x.FirstName)).result)
    f.map(seq => seq.map(reporterToRow))
  }

  private def reporterToRow(reporter: Reporter): Reporter = {
    Reporter(reporter.reporterID, reporter.firstName, reporter.lastName)
  }

  /*
  def GetArticleByID(articleID: Int): Future[NewsArticle] = {
    db.run(newsArticles.filter(item => item.ArticleID == articleID).result.head)
  }
 */

  /*
  def findById(id: Int): Future[Option[NewsArticle]] = {

    db.run(newsArticles.filter(_.articleID == id).result.headOption)
  }
  */

}
