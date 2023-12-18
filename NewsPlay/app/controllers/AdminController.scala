package controllers

import models.{AdminOptions, NewsArticle}

import javax.inject._
import play.api._
import play.api.data.Form
import play.api.mvc._
import services.NewsService
import shared.SharedService

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class AdminController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with play.api.i18n.I18nSupport {
  import models.NewsArticleForm


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

  def createNews() = Action { implicit request: Request[AnyContent] =>
    val newsArticle = SharedService.defaultNewsArticle()

    val adminOptions = new AdminOptions("Create News")

    Ok(views.html.admin.newsedit(newsArticle, adminOptions, NewsArticleForm.form))
  }

  def editNews(articleID: Integer) = Action { implicit request: Request[AnyContent] =>
    val optNewsArticle = services.NewsService.GetNewsByID(articleID)

    val blankArticle = SharedService.createNewsArticle(0, "Not Found", "<p>This article could not be found</p>", 0, "", "")
    val newsArticle = optNewsArticle.getOrElse(blankArticle)

    val adminOptions = new AdminOptions("Edit News")
    adminOptions.listOfReporters = Option(services.NewsService.GetListOfReporters())

    var currentForm = NewsArticleForm.form
    currentForm.fill(NewsArticleForm.Data.apply(0, "My title", "My reporter", "my content"))

    //Ok(views.html.admin.newsedit(newsArticle, adminOptions, NewsArticleForm.form))
    Ok(views.html.admin.newsedit(newsArticle, adminOptions, currentForm))
  }


  def saveNews() = Action { implicit request: Request[AnyContent] =>
    //val formData: NewsArticleForm.Data = NewsArticleForm.form.bindFromRequest.get
    val formData: NewsArticleForm.Data = NewsArticleForm.form.bindFromRequest().get

    println(formData)
    

    Redirect(routes.AdminController.index())
  }

  def deleteNewsByID(articleID: Integer) = Action { implicit request: Request[AnyContent] =>
    NewsService.DeleteNewsByID(articleID)

    Redirect(routes.AdminController.index())
  }

}