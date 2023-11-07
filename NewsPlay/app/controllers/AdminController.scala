package controllers

import models.{AdminOptions, NewsArticle}

import javax.inject._
import play.api._
import play.api.mvc._
import shared.SharedService

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class AdminController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    val list = services.NewsService.GetNews();
    Ok(views.html.admin.index(list))
  }

  def createnews() = Action { implicit request: Request[AnyContent] =>
    val newsArticle = SharedService.defaultNewsArticle()

    val adminOptions = new AdminOptions("Create News")

    Ok(views.html.admin.newsedit(newsArticle, adminOptions))
  }

  def editnews(articleID: Integer) = Action { implicit request: Request[AnyContent] =>
    val optNewsArticle = services.NewsService.GetNewsByID(articleID)
    var blankArticle = new NewsArticle("Not Found", "Not Found", 0)
    blankArticle.content = "<p>This article could not be found</p>"

    val newsArticle = optNewsArticle.getOrElse(blankArticle)

    val adminOptions = new AdminOptions("Edit News")

    Ok(views.html.admin.newsedit(newsArticle, adminOptions))
  }

  def savenews() = Action { implicit request: Request[AnyContent] =>
    Redirect(routes.AdminController.index())
  }

}