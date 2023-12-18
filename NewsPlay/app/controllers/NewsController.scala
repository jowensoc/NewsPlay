package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import models.NewsArticle
import shared.SharedService

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class NewsController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    val list = services.NewsService.GetNews();
    Ok(views.html.news(list))
  }

  def article(articleID: Int) = Action { implicit request: Request[AnyContent] =>
    val optNewsArticle = services.NewsService.GetNewsByID(articleID)
    val blankArticle = SharedService.createNewsArticle(0, "Not Found", "<p>This article could not be found</p>", 0, "", "")

    val newsArticle = optNewsArticle.getOrElse(blankArticle)

    Ok(views.html.article(newsArticle))
  }

  def reporter(reporterShortName: String) = Action { implicit request: Request[AnyContent] =>
    val cleanReporterName = reporterShortName.replace(" ", "-").trim.toLowerCase()
    val listOfArticles = services.NewsService.GetNewsByReporterShortName(cleanReporterName)
    val reporterFullName = if (listOfArticles.nonEmpty) listOfArticles.head.reporterFirstName + " " + listOfArticles.head.reporterLastName else "Reporter Name"

    Ok(views.html.reporter(listOfArticles, reporterFullName))
  }
}
