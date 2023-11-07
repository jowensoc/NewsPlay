package models

class AdminOptions(heading: String) {
  var listOfReporters : Option[List[Reporter]] = None
  val pageHeading: String = heading
}
