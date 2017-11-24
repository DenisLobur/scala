package essential_slick

import slick.jdbc.PostgresProfile.api._

case class UserMessage(senderId: Long, content: String, roomId: Option[Long] = None, id: Long = 0L)

class UserMessageTable(tag: Tag) extends Table[UserMessage](tag, "user_messages") {
  val id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  val senderId = column[Long]("senderId")
  val content = column[String]("content")
  val roomId = column[Option[Long]]("roomId")

  def sender = foreignKey("sender_fk", senderId, UserTable.users)(_.id)

  def * = (senderId, content, roomId, id) <> (UserMessage.tupled, UserMessage.unapply)
}

object UserMessageTable {
  lazy val userMessages = TableQuery[UserMessageTable]
  lazy val insertUserMessage = userMessages returning userMessages.map(_.id)
}


