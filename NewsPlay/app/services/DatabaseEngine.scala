package services

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import dao.NewsArticle
import slick.lifted.ProvenShape

@Singleton
class DatabaseEngine @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  class NewsArticles(tag: Tag) extends Table[NewsArticle](tag, "NewsArticle") {

    def ArticleID = column[Int]("ArticleID", O.PrimaryKey, O.AutoInc)
    def Title = column[String]("Title")
    def ArticleContent = column[String]("ArticleContent")
    def ReporterID = column[Int]("ReporterID")
    //def * = (ArticleID, Title, ArticleContent, ReporterID) <> (NewsArticle.tupled, NewsArticle.unapply)
    def * = (ArticleID, Title, ArticleContent, ReporterID) <> ((NewsArticle.apply _).tupled, NewsArticle.unapply)
  }

  private val newsArticles = TableQuery[NewsArticles]

  def GetArticles(): Future[Seq[NewsArticle]] = {
    db.run(newsArticles.result)
  }

  def GetArticleByID(articleID: Int): Future[NewsArticle] = {
    db.run(newsArticles.filter(item => item.ArticleID == articleID).result.head)
  }

  /*
  def findById(id: Int): Future[Option[NewsArticle]] = {

    db.run(newsArticles.filter(_.articleID == id).result.headOption)
  }
  */

}
