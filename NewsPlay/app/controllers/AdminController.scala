package controllers

import models.{AdminOptions, NewsArticle}

import javax.inject._
import play.api._
import play.api.data.Form
import play.api.mvc._
import services.{DatabaseEngine, NewsService, RootNewsService}
import shared.SharedService

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class AdminController @Inject()(val controllerComponents: ControllerComponents)(implicit dbEngine: DatabaseEngine) extends BaseController with play.api.i18n.I18nSupport {
  import models.NewsArticleForm
  implicit val ec: ExecutionContextExecutor = ExecutionContext.global

  private val newsService = new RootNewsService()

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action.async { implicit request: Request[AnyContent] =>
    val list = newsService.GetNews()

    list.map(item => Ok(views.html.admin.index(item)))
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
    newsService.DeleteNewsByID(articleID)

    Redirect(routes.AdminController.index())
  }

}