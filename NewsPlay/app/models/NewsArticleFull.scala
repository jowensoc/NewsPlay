package models

import play.api.data.Form
import play.api.data.Forms.{mapping, number, text}

case class NewsArticleFull(articleID: Int, title: String, reporterName: String, articleContent: String)

object NewsArticleForm {
  val form: Form[NewsArticleFull] = Form(
    mapping(
      "articleID" -> number,
      "title" -> text,
      "reporterName" -> text,
      "articleContent" -> text
    )(NewsArticleFull.apply)(NewsArticleFull.unapply)
  )
}
