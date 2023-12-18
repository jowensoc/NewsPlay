package shared

import models.NewsArticle

object SharedService {

  def createNewsArticle(articleID: Int, title: String, articleContent: String, reporterID: Int, reporterFirstName: String,reporterLastName: String): NewsArticle = {
    val newsArticle = new NewsArticle(articleID, title, articleContent, reporterID, reporterFirstName, reporterLastName)

    newsArticle
  }

  def defaultNewsArticle(): NewsArticle = {
    val newsArticle = new NewsArticle(0, "", "", 0, "", "")

    newsArticle
  }

  def generateShortName(fullName: String): String = {
    if (fullName.isEmpty) {
      return  ""
    }

    fullName.replace(" ", "-").trim.toLowerCase()
  }

}
