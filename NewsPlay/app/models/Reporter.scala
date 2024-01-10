package models

import shared.SharedService

case class Reporter(reporterID: Int, firstName: String, lastName: String) {
  val fullName = getFullName()
  val shortName: String = getShortName()

  private def getFullName(): String = {
    val result = firstName + " " + lastName

    result.trim
  }

  private def getShortName(): String = {
    val result = firstName + " " + lastName

    SharedService.generateShortName(result)
  }
}
