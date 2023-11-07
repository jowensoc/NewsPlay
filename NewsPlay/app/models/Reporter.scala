package models

import shared.SharedService

class Reporter(val fullName: String) {
  val shortName: String = SharedService.generateShortName(fullName)
}
