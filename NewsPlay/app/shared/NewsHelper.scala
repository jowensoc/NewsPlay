package shared

import play.api.data.Form
import models.NewsArticleForm

object NewsHelper {

  def dumpFormData(form: Form[NewsArticleForm.Data], keyName: String): String = {
    val sb = new StringBuilder()

    sb.append("<p>")
    sb.append(keyName + " = ")
    sb.append("Name: " + form(keyName).name + " ")
    sb.append("Value: " + form(keyName).value + " ")
    sb.append("</p>")

    sb.toString
  }

}
