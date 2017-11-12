package dataRoot.db.model

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

case class Genre(id: Option[Long], title: String, description: String)

final class GenreTable(tag: Tag) extends Table[Genre](tag, "genre") {
  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def title = column[String]("title")

  def description = column[String]("description")

  def * = (id, title, description).mapTo[Genre]
}
