package models

import play.api.data.Form
import play.api.data.Forms.{mapping, number, text}

case class BasicForm(name: String, age: Int)

// this could be defined somewhere else,
// but I prefer to keep it in the companion object
object BasicForm {
  val form: Form[BasicForm] = Form(
    mapping(
      "name" -> text,
      "age" -> number
    )(BasicForm.apply)(BasicForm.unapply)
  )
}
