package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class AdminControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "Admin GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new AdminController(stubControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/admin"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Edit Articles")
    }

    "render the index page from the application" in {
      val controller = inject[AdminController]
      val home = controller.index().apply(FakeRequest(GET, "/admin"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Edit Articles")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/admin")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Edit Articles")
    }
  }
}
