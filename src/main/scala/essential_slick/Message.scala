package essential_slick

import slick.jdbc.PostgresProfile.api._

case class Message(sender: String, content: String, id: Long = 0L)

case class TextOnly(id: Long, content: String)

class MessageTable(tag: Tag) extends Table[Message](tag, "messages") {
  val id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  val sender = column[String]("sender")
  val content = column[String]("content")

  def * = (sender, content, id) <> (Message.apply _ tupled, Message.unapply)
}