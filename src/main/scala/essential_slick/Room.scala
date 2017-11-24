package essential_slick

import slick.jdbc.PostgresProfile.api._

case class Room(title: String, id: Long = 0L)

class RoomTable(tag: Tag) extends Table[Room](tag, "rooms"){
  val id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  val title = column[String]("title")

  def * = (title, id) <> (Room.tupled, Room.unapply)
}

object RoomTable {
  lazy val rooms = TableQuery[RoomTable]
  lazy val insertRoom = rooms returning rooms.map(_.id)
}
