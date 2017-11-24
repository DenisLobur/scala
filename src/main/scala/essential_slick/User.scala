package essential_slick

import slick.jdbc.PostgresProfile.api._

case class User(name: String, id: Long = 0L)

class UserTable(tag: Tag) extends Table[User](tag, "users") {
  val id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  val name = column[String]("name")

  def * = (name, id) <> (User.tupled, User.unapply)
}

object UserTable {
  lazy val users = TableQuery[UserTable]
  lazy val insertUsers = users returning users.map(_.id)
}
