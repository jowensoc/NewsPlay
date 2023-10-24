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
class NewsControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "NewsController GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new NewsController(stubControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("News Articles")
    }

    "render the index page from the application" in {
      val controller = inject[NewsController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("News Articles")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }
  }

  "NewsArticle Route GET" should {

    "render the article page from a new instance of controller by ID" in {
      val controller = new NewsController(stubControllerComponents())
      val home = controller.article(1).apply(FakeRequest(GET, "/news/1"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Jamie wins trip to New York")
    }

    "render the article page as not found" in {
      val controller = new NewsController(stubControllerComponents())
      val home = controller.article(0).apply(FakeRequest(GET, "/news/0"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Not Found")
    }
  }
}
