package models

import shared.SharedService

class NewsArticle(val title: String, val reporter: String, val articleID: Int) {

  var content: String = initialiseContent()
  val reportShortName: String = SharedService.generateShortName(reporter)

  def initialiseContent(): String = {
    val sb: StringBuilder = new StringBuilder()
    sb.append("<p>")
    sb.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
      "Morbi sodales, elit vel feugiat dapibus, purus libero pellentesque felis, nec malesuada elit orci eget purus. " +
      "Aliquam in ligula dignissim, interdum nulla id, fringilla massa. Vestibulum lacinia eget risus et pretium. Nulla facilisi. " +
      "Aliquam tincidunt posuere lacinia. Pellentesque mollis commodo mollis. Vestibulum vitae vestibulum ipsum, sed finibus nisi. " +
      "Cras sagittis sollicitudin ullamcorper. Vestibulum augue arcu, ultricies vitae neque vitae, gravida mollis est. " +
      "Curabitur id semper urna.")
    sb.append("</p>")

    sb.append("<p>")
    sb.append("Aliquam elementum orci ante, eget dictum eros pulvinar vehicula. Fusce fringilla risus vel turpis pellentesque, " +
      "eget sodales nunc dignissim. Suspendisse luctus felis vel massa volutpat, at hendrerit ipsum luctus. Quisque at imperdiet dolor. " +
      "Morbi sed enim elit. Aliquam consequat cursus turpis quis vulputate. Integer non nulla lobortis, dapibus quam at, sagittis nisl. " +
      "Proin quis venenatis libero. Donec vehicula ligula ac commodo suscipit. Orci varius natoque penatibus et magnis dis parturient montes," +
      " nascetur ridiculus mus.")
    sb.append("</p>")

    sb.append("<p>")
    sb.append("Etiam et commodo odio. Nullam dictum purus et urna ornare, vitae tempor ex tincidunt. Mauris massa nisl, mattis at dapibus vel," +
      " porttitor nec tortor. Nullam et ex ante. Integer dignissim risus dui, at placerat eros dictum vel. Integer quis gravida purus, a sodales" +
      " dolor. Vestibulum ante est, condimentum sit amet urna ut, iaculis elementum mi. Phasellus auctor odio sapien, vel molestie enim accumsan" +
      " quis. Phasellus quam ligula, hendrerit in eros et, pretium finibus libero. Sed auctor ut elit quis elementum. Lorem ipsum dolor " +
      "sit amet, consectetur adipiscing elit.")
    sb.append("</p>")

    sb.toString()
  }

}
