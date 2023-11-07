package shared

import models.NewsArticle

object SharedService {

  def createNewsArticle(articleID: Int, title: String, reporter: String, content: String): NewsArticle = {
    val newsArticle = new NewsArticle(title, reporter, articleID)
    newsArticle.content = content

    newsArticle
  }

  def defaultNewsArticle(): NewsArticle = {
    val newsArticle = new NewsArticle("", "", 0)
    newsArticle.content = ""

    newsArticle
  }

  def generateShortName(fullName: String): String = {
    if (fullName.isEmpty) {
      return  ""
    }

    fullName.replace(" ", "-").trim.toLowerCase()
  }

}
