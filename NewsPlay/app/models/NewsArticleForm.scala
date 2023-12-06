package models

object NewsArticleForm {

  import play.api.data._
  import play.api.data.Forms._
  import play.api.data.validation.Constraints._

  case class Data(articleID: Int, title: String, reporterName: String, articleContent: String)
  object Data {
    def unapply(data: Data): Option[(Int, String, String, String)] = {
      Some(data.articleID, data.title, data.reporterName, data.articleContent)
    }
  }

  val form: Form[Data] = Form(
    mapping(
      "articleID" -> number,
      "title" -> text,
      "reporterName" -> text,
      "articleContent" -> text
    )(Data.apply)(Data.unapply)
  )

}
